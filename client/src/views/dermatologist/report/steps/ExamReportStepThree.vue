<template>
    <b-col>
        <b-row>
            <h4><b-badge variant="success">Drug prescription</b-badge></h4>
        </b-row>
        <b-col class="mt-2" v-if="prescribedDrugs.length!=0" md="2">
            <b-row>
                <h5><b-badge variant="success">Prescribed drugs:</b-badge></h5>
            </b-row>
            <b-row  class="mt-2" v-for="drugPrescription in prescribedDrugs" :key="drugPrescription.drug.id">
                <h5><b-badge variant="success">{{drugPrescription.drug.name}} 
                    ({{drugPrescription.duration}}
                    {{drugPrescription.duration==1 ? 'day' : 'days'}}) </b-badge>
                </h5>
            </b-row>
            <b-row>
                <b-button size="sm" class="mt-2" @click="clearDrugs()">
                    Clear prescribed drugs
                </b-button>
            </b-row>
        </b-col>

         <b-col lg="4" class="my-3 mt-4">
            <b-form-group label="Drug:" label-for="filter-input" label-cols-sm="2" label-align-sm="right"
                label-size="sm" class="mb-0">
                <b-input-group size="sm">
                    <b-form-input id="filter-input"
                    v-model="filter" type="search" placeholder="Drug name or manufacturer..."
                    >
                    </b-form-input>

                    <b-input-group-append>
                        <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                    </b-input-group-append>
                </b-input-group>
            </b-form-group>
        </b-col>

        <b-table
            class="mt-2"
            striped hover
            sticky-header
            :dark="true"
            :items="drugs"
            :fields="fields"
            show-empty
            :filter="filter"
            empty-filtered-text="There are no drugs matching your query."
        >
            <template #cell(drugSpec)="row">
                <b-button size="sm" class="mr-1" @click="showDrugSpec(row.item,$event.target)">
                    Drug specification
                </b-button>
            </template>

            <template #cell(prescribe)="row">
                <b-button size="sm" @click="showDrugPrescription(row.item,$event.target)">
                    Prescribe
                </b-button>
            </template>

        </b-table>

        <b-modal size="lg" :title="drugSpecModal.title" :id="drugSpecModal.id" ok-only>
            <drug-specification :drug="drugSpecModal.drug"></drug-specification>
        </b-modal>
        <b-modal size="lg" :title="drugPrescriptionModal.title" :id="drugPrescriptionModal.id" ok-only>
            <drug-prescription :drug="drugPrescriptionModal.drug"></drug-prescription>
        </b-modal>

        <b-row align-h="center" class="mt-4" >
            <b-col sm="5">
                <b-button variant="success" @click="back()"> Go back to an examination information step </b-button>
            </b-col>
            <b-col sm="5">
                <b-button variant="success" @click="proceed()"> Proceed to an examination scheduling step </b-button>
            </b-col>
            
        </b-row>
    </b-col>
</template>

<script>
import axios from 'axios'
import { api } from '../../../../api.js'
import DrugSpecification from '../../../../components/report/DrugSpecification.vue'
import DrugPrescription from '../../../../components/report/DrugPrescription.vue'

export default {
    name: 'ExamReportStepThree',
    components: {DrugSpecification, DrugPrescription},
    data() {
        return {
            prescribedDrugs:[],
            drugs:null,
            fields:[
                { key: 'name', label: 'Drug name', sortable: true },
                { key: 'manufacturer', label: 'Manufacturer', sortable: true},
                { key: 'drugSpec', label: 'Drug specification'},
                { key: 'prescribe', label:'Prescribe'}
            ],
            filter :null,
            filterOn: ['name','manufacturer'],
            drugSpecModal: {
                id: 'drug-specifications-modal',
                drug: null,
                title: ''
            },
            drugPrescriptionModal:{
                id: 'drug-prescription-modal',
                drug: null,
                title: ''
            }
        }
    },
    methods:{
        proceed(){
            this.$router.push({ name: 'exam-report-step-four' })
        },
        back(){
            this.$router.push({ name: 'exam-report-step-two' })
        },
        showDrugSpec(item,button){
            this.drugSpecModal.drug = item
            this.drugSpecModal.title = 'Drug ' + item.name
            this.$root.$emit('bv::show::modal',this.drugSpecModal.id,button)
        },
        showDrugPrescription(item,button){
            this.drugPrescriptionModal.drug = item
            this.drugPrescriptionModal.title ='Prescribe ' +  item.name
            this.$root.$emit('bv::show::modal',this.drugPrescriptionModal.id,button)
        },
        fetchDrugs(){
            axios.get(api.drugs.root).then(res=>{
                this.drugs = res.data
                this.updatePrescribed()
            })
        },
        clearDrugs(){
            this.prescribedDrugs.forEach(drugPrescription => {
                this.drugs.push(drugPrescription.drug)
            })
            this.prescribedDrugs=[]
            this.$store.commit('clearPrescribedDrugs')
        },
        updatePrescribed(){
            this.prescribedDrugs=this.$store.state.report.appointmentReport.prescribedDrugs

            if(this.prescribedDrugs!=null)
                this.drugs.forEach(drug => {
                    this.prescribedDrugs.forEach(prescribedDrug => {
                        if(drug.id == prescribedDrug.drug.id){
                            var index = this.drugs.indexOf(drug)
                            if(index > -1)
                                this.drugs.splice(index,1)
                        }
                    })
                })

        }
    },
    mounted(){
        this.fetchDrugs()
        
        this.$root.$on('update::prescribed',() =>{
            this.updatePrescribed()
        })
    }

}
</script>

<style scoped>


</style>