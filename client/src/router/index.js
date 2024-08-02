import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import views
import AboutView from '../views/AboutView.vue';
import HowItWorksView from '../views/HowItWorksView.vue';
import DashboardView from '../views/DashboardView.vue';
import LoginView from '../views/LoginView.vue';
import SignUpView from '../views/SignUpView.vue';
import ProfileView from '../views/ProfileView.vue';
import HomePageView from '../views/HomePageView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const routes = [
    {
        path: '/',
        name: 'homepage',
        component: HomePageView,
        meta:{
            requiresAuth: false
        }
    },{
        path: '/about',
        name: 'about',
        component: AboutView,
        meta:{
            requiresAuth: false
        }
    },
    {
        path: '/how-it-works',
        name: 'howItWorks',
        component: HowItWorksView,
        meta:{
            requiresAuth: false
        }
    },
    {
        path: '/dashboard',
        name: 'dashboard',
        component: DashboardView,
        meta:{
            requiresAuth: true
        }
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView,
        meta:{
            requiresAuth: false
        }
    },
    {
        path: '/signup',
        name: 'signup',
        component: SignUpView,
        meta:{
            requiresAuth: false
        }
    },
    {
        path: '/memories',
        name: 'profile',
        component: ProfileView,
        meta:{
            requiresAuth: true
        }
    },
];

// Create router
const router = createRouter( {
    history: createWebHistory(),
    routes: routes
});

router.beforeEach((to) => {

    // Get the Vuex store
    const store = useStore();
  
    // Determine if the route requires Authentication
    const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  
    // If it does and they are not logged in, send the user to "/login"
    if (requiresAuth && store.state.token === '') {
      return {name: "login"};
    }
    // Otherwise, do nothing and they'll go to their next destination
  });

  export default router;