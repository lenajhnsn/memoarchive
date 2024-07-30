/<template>
    <div>
        <AppHeader />
        <HowItWorks></HowItWorks>
        <AppFooter />
    
    </div>
</template>

<script>
import AppHeader from '../components/AppHeader.vue';
import AppFooter from '../components/AppFooter.vue';
import HowItWorks from '../components/HowItWorks.vue';


export default {
    components: {
        AppHeader,
        HowItWorks,
        AppFooter
    }
    }
</script>

<style scoped>
/* TODO: Add additional styling here, if needed. */
</style>