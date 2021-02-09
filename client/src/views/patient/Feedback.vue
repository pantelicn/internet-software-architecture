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
export default {
    data: function () {
        return {
            pharmacists: [],
            dermatologists: [],
            drugs: [],
            pharmacies: []
        }
    }
}
</script>