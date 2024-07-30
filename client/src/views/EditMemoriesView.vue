<template>
    <AppHeader />
    <EditMemories />
    <AppFooter />
</template>

<script>
import EditMemories from '../components/EditMemories.vue';
import AppHeader from '../components/AppHeader.vue';
import AppFooter from '../components/AppFooter.vue';

export default {
        components: {
    Header,
    EditMemories,
    AppHeader,
    AppFooter
}
    }
</script>

<style scoped>

</style>