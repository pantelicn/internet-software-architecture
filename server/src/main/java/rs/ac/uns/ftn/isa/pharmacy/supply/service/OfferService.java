package rs.ac.uns.ftn.isa.pharmacy.supply.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.supply.dto.OfferMapper;
import rs.ac.uns.ftn.isa.pharmacy.supply.dto.OfferRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.supply.exceptions.*;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.Offer;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.OrderedDrug;
import rs.ac.uns.ftn.isa.pharmacy.supply.domain.SupplierStock;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.OfferRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.OrderedDrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.supply.repository.SupplierStockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OrderedDrugRepository orderedDrugRepository;
    private final SupplierStockRepository supplierStockRepository;
    private final OfferMapper offerMapper;

    public OfferService(
            OfferRepository offerRepository,
            OrderedDrugRepository orderedDrugRepository,
            SupplierStockRepository supplierStockRepository,
            OfferMapper offerMapper
    ) {
        this.offerRepository = offerRepository;
        this.orderedDrugRepository = orderedDrugRepository;
        this.supplierStockRepository = supplierStockRepository;
        this.offerMapper = offerMapper;
    }

    public void create(OfferRequestDto dto)
            throws InvalidEntityException, LateDeadlineException, InvalidForeignKeyException,
            EntityExistsException, InsufficientDrugAmountException, ExpiredException
    {
        Offer offer = offerMapper.dtoToObject(dto);
        offer.validateBeforeChange();
        if (offerExists(offer.getPurchaseOrder().getId(), offer.getSupplier().getPersonId()))
            throw new EntityExistsException("Offer");
        if (isSupplierStockedUp(offer.getPurchaseOrder().getId(), offer.getSupplier().getPersonId())) {
            offer.setStatus(Offer.Status.PENDING);
            offerRepository.save(offer);
        }
        else throw new InsufficientDrugAmountException();
    }

    public void update(OfferRequestDto dto)
            throws EntityNotFoundException, LateDeadlineException, InvalidEntityException, ExpiredException
    {
        Offer offer = getOffer(dto.getPurchaseOrderId(), dto.getSupplierId());
        if (offer == null) throw new EntityNotFoundException("Offer");
        offer.setPrice(dto.getPrice());
        offer.setDeliveryDeadline(dto.getDeliveryDeadline());
        offer.validateBeforeChange();
        offerRepository.save(offer);
    }

    public List<Offer> getByStatus(Offer.Status status, long supplierId) {
        return offerRepository.getByStatusAndSupplier(status, supplierId);
    }

    private boolean offerExists(long purchaseOrderId, long supplierId) {
        return getOffer(purchaseOrderId, supplierId) != null;
    }

    private Offer getOffer(long purchaseOrderId, long supplierId) {
        return offerRepository.getByPurchaseOrderAndSupplier(purchaseOrderId, supplierId);
    }

    private boolean isSupplierStockedUp(long purchaseOrderId, long supplierId) {
        List<OrderedDrug> orderedDrugs = orderedDrugRepository.getByPurchaseOrderId(purchaseOrderId);
        List<SupplierStock> supplierStocks = supplierStockRepository.getBySupplierId(supplierId);

        for (OrderedDrug orderedDrug : orderedDrugs) {
            Optional<SupplierStock> drugInStock = supplierStocks.stream().findAny();
            if (drugInStock.isEmpty() || drugInStock.get().getAmount() < orderedDrug.getAmount())
                return false;
        }

        return true;
    }

    public List<Offer> getBySupplierId(long supplierId) {
        return offerRepository.getBySupplier(supplierId);
    }

    public List<Offer> getByPurchaseOrderId(long purchaseId) {
        return offerRepository.getByPurchaseOrder(purchaseId);
    }

    public Boolean checkSupplies(long purchaseOrderId, long supplierId) {
        return isSupplierStockedUp(purchaseOrderId, supplierId);
    }
}
