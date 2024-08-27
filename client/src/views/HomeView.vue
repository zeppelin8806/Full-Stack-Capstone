<template>
  <div class="home">
    <div id="heading-line">
      <h1>
        Welcome to the StormLight Archive
      </h1>
      <p>Set in the richly detailed world of Roshar, where unique cultures and 
        peoples are shaped by the harsh and magical environment. The world is ravaged 
        by powerful storms, known as highstorms, which have led to the development of 
        societies that are both resilient and distinct.</p>
      <img
        src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*QD5qZosh_aE2VPVSxGYmxQ.jpeg"
        alt=""
      />
      <nav id="nav-cards">
        <router-link
          class="view-card"
          :to="{ name: 'characters' }">
          <img :src="person" alt="" />
          <h3>CHARACTERS</h3>
        </router-link>
        <router-link class="view-card" :to="{ name: 'shards' }">
          <img :src="shard" alt="">
          <h3>SHARDS</h3>
          </router-link>
        <router-link
          class="view-card" :to="{ name: 'orders' }">
          <img :src="order" id="order"/>
          <h3>KNIGHTS RADIANT</h3>
        </router-link>
        <router-link class="view-card" :to="{ name: 'location' }">
          <img :src="site" alt="" />
          <h3>LOCATION</h3>
        </router-link>
      </nav>
    </div>
  </div>
</template>

<script>
import StormlightService from "../services/StormlightService";
import utility from "../utility/utility";

export default {
  data() {
    return {
      isLoading: false,
      cardView: true,
      orders: [],
      site: "https://uploads.coppermind.net/thumb/OB_Roshar.jpg/1920px-OB_Roshar.jpg",
      shard: "https://uploads.coppermind.net/Adolin_Chapters.svg",
      person: "https://uploads.coppermind.net/Lopen_Chapters.svg",
      order: "https://uploads.coppermind.net/Jeseh_glyph.svg"
    };
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.token.length > 0;
    },
  },
  methods: {
    getOrders() {
      StormlightService.getOrders()
      .then((response) => {
        this.orders = response.data;
      })
      .catch((error) => {
      utility.handleAxiosError(error, "Get orders");
    });
    },
    getCharacters() {
      StormlightService.getCharacters()
      .then((response) => {
        this.characters = response.data;
      })
      .catch((error) => {
      utility.handleAxiosError(error, "Get characters");
    });
    },
  },
  created() {
    this.getOrders();
    this.getCharacters();
  },
};
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  width: 100%;
  grid-area: main;
}

h1 {
  font-size: 48px;
  margin-bottom: 5px;
}

img {
  width: 80%;
  align-self: center;
}

#heading-line {
  display: flex;
  flex-direction: column;
  padding-left: 10px;
  margin-left: 50px;
}

.view-card {
  background-color: white;
  display: flex;
  flex-direction: column;
  margin: 10px;
  list-style: none;
  border: 1px solid lightgray;
  border-radius: 10px;
  height: 300px;
  width: 300px;
  align-items: center;
  justify-content: center;
  filter: drop-shadow(0px 0px 10px black);
  transition: all 0.5s;
  text-decoration: none;
  color: black;

  > h3 {
    display: none;
  }

  > img {
    width: 250px;
  }
}
.view-card:hover {
  filter: drop-shadow(0px 0px 30px black);
  cursor: pointer;

  > h3 {
    display: contents;
  }
}

#nav-cards {
  display: flex;
  flex-wrap: wrap;
  padding-left: 50px;
  justify-content: center;
  padding-left: 0;
}

#order{
  height: 250px;
}

@media only screen and (max-width: 426px) {
  div {
    margin: 0;
    text-align: center;
    padding-left: 0;
  }

  main > nav > ul {
    padding: 0;
  }
  
  #heading-line {
  padding-left: 0px;
  margin-left: 0px;

  #nav-cards{
    padding-left: 0px;
  }
}
}
</style>
