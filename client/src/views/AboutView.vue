/<template>
    <div>
        <!-- Include reusable components-->
        <AppHeader />
        <EditMemories></EditMemories>
        <AppFooter />
    </div>
</template>

<script>
import AppHeader from '../components/AppHeader.vue';
import  AppFooter from '../components/AppFooter.vue';
import EditMemories from '../components/EditMemories.vue';

    export default {
    components: { 
        AppHeader,
        AppFooter,
        EditMemories
     }
}
</script>

<style scoped>
/* TO DO: See if any additional styles are needed here */
</style>