<template>
  <div id="body">
    <AppHeader/>
    <AppNavLeft/>
    <AppSearch/>
    
    <!-- <nav>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          Logout
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link>
    </nav> -->
    <main>
      <router-view />
    </main>
    <AppFooter/>
  </div>
</template>

<script>
import AppHeader from './components/AppHeader.vue';
import AppNavLeft from './components/AppNavLeft.vue';
import AppSearch from './components/AppSearch.vue';
import AppFooter from './components/AppFooter.vue';


export default{
    data(){
        return{
            
        }
    },
    components: {
        AppHeader,
        AppNavLeft,
        AppSearch,
        AppFooter
    },
    methods: {
        
    }
}
</script>

<style scoped>
body{
  margin: 0;
}

#body{
    
    display: grid;
    grid-template-columns: 1fr 7fr;
    grid-template-rows: 1fr 40px auto 40px;
    grid-template-areas: 
        "banner banner"
        "aside-left aside-top"
        "aside-left main"
        "aside-left footer";
    margin: 0;
    padding-left: 0;
}

#app-header{
    grid-area: banner;
}

#aside-left{
    grid-area: aside-left;
}

#aside-top{
    grid-area: aside-top;
}

#footer{
    grid-area: footer;
}



@media only screen and (max-width: 426px){
    #body{
        grid-template-columns: 100%;
        grid-template-rows: 1fr 60px 60px auto 60px;
        grid-template-areas:
            "banner"
            "aside-left"
            "aside-top"
            "main"
            "footer";
        margin: 0;
    }

    
    div{
        padding: 0;
    }

    
/* 
    #aside-left>nav>a{
        flex-direction: row;
        align-items: center;
        list-style: none;
        padding: 0;
        flex-wrap: wrap;
        justify-content: space-evenly;
        margin: 5px 0 10px 0;
    }

    #aside-left>ul>li{
        margin: 5px 5px 0 5px;
    } */
}


</style>