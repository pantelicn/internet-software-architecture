<template>
    <div class="container">
        <div class="row">
            <div class=col>
                Rate dermatologists
            </div>
            <div class=col>
                Rate pharmacists
            </div>
            <div class=col>
                Rate drugs
            </div>
            <div class=col>
                Rate pharmacies
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../api.js'

export default {
    data: function () {
        return {
            pharmacists: [],
            dermatologists: [],
            drugs: [],
            pharmacies: []
        }
    },
    methods: {
        fetchRateable: function () {
            axios.get(api.rating.root)
            .then(response => {
                let rateable = response.data
                this.pharmacists = rateable.pharmacists
                this.dermatologists = rateable.dermatologists
                this.drugs = rateable.drugs
                this.pharmacies = rateable.pharmacies
            })
        }
    },
    mounted: function () {
        this.fetchRateable()
    }
}
</script>