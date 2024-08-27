<template>
  <aside id="aside-top">
    <div id="search-bar">
      <input type="search" id="search" placeholder="SEARCH" />
    </div>
    <button type="submit" id="search-submit-btn">SEARCH</button>
    <div>
      <router-link class="login-signup" v-bind:to="{ name: 'login' }" v-if="!$store.getters.isAuthenticated">LOGIN</router-link>
      <router-link class="login-signup" v-bind:to="{ name: 'register' }" v-if="!$store.getters.isAuthenticated"> | SIGNUP</router-link>
      <router-link class="login-signup" v-bind:to="{ name: 'logout' }" v-else>LOGOUT</router-link>
    </div>
  </aside>
</template>

<script>
export default {};
</script>

<style scoped>
#aside-top {
  display: flex;
  justify-content: flex-end;
  width: 100%;
  height: 60px;
  align-items: center;

  background-color: rgb(125, 144, 173);
}

#aside-top > * {
  margin-right: 15px;
}

.login-signup {
  font-size: 1.17em;
  text-decoration: none;
  color: black;
  font-weight: bold;
}

#search {
  width: auto;
  height: 25px;
  border-radius: 5px;
}

#search-submit-btn {
  width: 80px;
  height: 30px;
  background-color: rgb(199, 225, 250);
  border-radius: 5px;
  font-weight: bold;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}

@media only screen and (max-width: 426px) {
  #aside-top {
    width: auto;
    padding: 0 5px 0 5px;
    justify-content: space-between;
  }

  .login-signup {
    font-size: small;
    margin-right: 0;
  }
  #aside-top > * {
    margin-right: 0;
  }

  #search-bar {
    margin: 0;
  }

  #search {
    height: 20px;
    width: auto;
    margin-right: 10px;
  }

  label {
    margin-right: 10px;
  }

  #search-submit-btn {
    height: 25px;
    margin-right: 10px;
  }
}
</style>
