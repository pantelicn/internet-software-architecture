package rs.ac.uns.ftn.isa.pharmacy.domain.supply.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.dto.OfferMapper;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.dto.OfferRequestDto;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InsufficientDrugAmountException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.InvalidForeignKeyException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.LateDeadlineException;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.Offer;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.OrderedDrug;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.model.SupplierStock;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.repository.OfferRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.repository.OrderedDrugRepository;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.repository.SupplierStockRepository;

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

    public void create(OfferRequestDto offerRequestDto)
            throws InsufficientDrugAmountException, LateDeadlineException, InvalidForeignKeyException
    {
            Offer offer = offerMapper.dtoToObject(offerRequestDto);
            if (offer.isInPast())
                throw new LateDeadlineException();
            if (isSupplierStockedUp(offer.getPurchaseOrder().getId(), offer.getSupplier().getId())) {
                offer.setStatus(Offer.Status.PENDING);
                offerRepository.save(offer);
            }
            else throw new InsufficientDrugAmountException();
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
        return offerRepository.getBySupplierId(supplierId);
    }

    public List<Offer> getByPurchaseOrderId(long purchaseId) {
        return offerRepository.getByPurchaseOrderId(purchaseId);
    }
}
