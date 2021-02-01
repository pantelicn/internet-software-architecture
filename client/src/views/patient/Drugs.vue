<template>
<div class="container">
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
        <label class="btn btn-info" active>
            <input type="radio" v-model="view" value="reserve" checked> Reserve drug
        </label>
        <label class="btn btn-info">
            <input type="radio" v-model="view" value="view"> Your reservations
        </label>
    </div>
    <div v-if="view == 'reserve'">
        <div class="form-inline d-flex justify-content-center m-3">
            <input type="text" class="form-control" placeholder="Search" v-model="searchString"/>
            <button class="btn btn-success ml-1" @click="search">Search</button>
        </div>
    </div>
    <div v-else>

    </div>
</div>
</template>

<script>
import { api } from '../../api.js'
import axios from 'axios'

export default {
    data: function () {
        return {
            view: 'reserve',
            drugs: [],
            searchString: ''
        }
    },
    methods: {
        search: function () {
            axios.get(api.drugs.search + '/' + this.searchString)
            .then(response => {
                this.drugs = response.data
            })
        }
    }
}
</script>