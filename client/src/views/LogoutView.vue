/<template>
    <!-- When a user logs out, redirect to HomePage -->
    <div>
       <HomePage />   
    </div>
</template>

<script>
import HomePage from '../components/HomePage.vue';


export default {
    components: {
    HomePage
    }
    }
</script>

<style scoped>
/* TODO: Add additional styling here, if needed. */
</style>