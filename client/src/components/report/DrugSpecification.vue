<template>
     <b-container>
        <b-list-group v-if="drug">
            <b-list-group-item>Name: {{drug.name}} </b-list-group-item>
            <b-list-group-item>Manufacturer: {{drug.manufacturer}} </b-list-group-item>
            <b-list-group-item>Notes: {{drug.additionalNotes}}</b-list-group-item>
            <b-list-group-item>Drug type: {{drug.drugType}}</b-list-group-item>
            <b-list-group-item>Intake type: {{drug.intakeType}}</b-list-group-item>
            <b-list-group-item >
                <div v-for="(key,value) in drug.ingredients" :key="key">
                    <div v-if="value==0">Ingredients:</div>
                     {{key}}
                </div>
            </b-list-group-item>
            <b-list-group-item>Requires prescription:
                {{drug.requiresPrescripition ? "Yes" : "No"}}
            </b-list-group-item>
        </b-list-group>
    </b-container>
</template>

<script>
export default {
    name: 'DrugSpecification',
    props:{
        drug: Object
    }

}
</script>

<style>

</style>