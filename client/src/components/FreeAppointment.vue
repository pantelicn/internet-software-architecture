<template>
<div class="border border-secondary">
    {{appointment.start}}
</div>
</template>

<script>
export default {
    props: {
        appointment: Object
    }
}
</script>