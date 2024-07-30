/<template>
    <div>
        <AppHeader />
        <HomePage />
        <AppFooter />
    
    </div>
</template>

<script>
import AppHeader from '../components/AppHeader.vue';
import AppFooter from '../components/AppFooter.vue';
import HomePage from '../components/HomePage.vue';


export default {
    components: {
    AppHeader,
    AppFooter,
    HomePage
}
    }
</script>

<style scoped>
/* TODO: Add additional styling here, if needed. */
</style>