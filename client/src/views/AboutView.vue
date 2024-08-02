/<template>
    <div>
        <!-- Include reusable components-->
        <AppHeader />
        <AppAbout />
        <AppFooter />
    </div>
</template>

<script>
import AppHeader from '../components/AppHeader.vue';
import  AppFooter from '../components/AppFooter.vue';
import AppAbout from '../components/AppAbout.vue';

    export default {
    components: { 
        AppHeader,
        AppFooter,
        AppAbout
     }
}
</script>

<style scoped>
/* TO DO: See if any additional styles are needed here */
</style>