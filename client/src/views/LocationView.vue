<template>
    <main>
      <div>
        <h1>ROSHAR</h1>
        <div id="map">
          <p>
            Roshar is a vibrant and complex world central to The Stormlight Archive series, 
            distinguished by its unique geography, ecology, and climate. This planet is 
            characterized by its harsh, storm-battered landscape and the fascinating 
            adaptations of its flora and fauna.
          </p>
          <img :src="site" alt="">
        </div>
      </div>
      <nav>
        <ul id="orders">
          <li v-for="location in locations" :key="location.location_id" :class=click v-on:click="toggleClass">
            <h3>{{ location.name }}</h3>
            <h4>Population: {{ location.population }}</h4>
            </li>
        </ul>
      </nav>
    </main>
</template>

<script>
import StormlightService from '../services/StormlightService';
import utility from '../utility/utility';

export default {
  data(){
    return{
      locations: [],
      site: "https://uploads.coppermind.net/Roshar.jpg"
    }
  },
  methods: {
    getLocations(){
      StormlightService
      .getLocation()
      .then((response) => {
        this.locations = response.data;
      })
      .catch((error) => {
      utility.handleAxiosError(error, "Get location");
    });
    }
  },
  created() {
    this.getLocations();
  }
}
</script>

<style scoped>
main {
    display: flex;
    flex-direction: column;
    width: 100%;
    grid-area: main;
}

h1{
    font-size: 48px;
    margin-bottom: 5px;
}

#map{
  display: flex;
  align-items: center;
}

img{
  width: 80%;
}

div {
    display: flex;
    flex-direction: column;
    padding-left: 10px;
    margin-left: 50px;
}

main>nav>ul{
    display: flex;
    flex-wrap: wrap;
    padding-left:50px;
    justify-content: center;
}

main>nav>ul>li{
    background-color: white;
    display: flex;
    flex-direction: column;
    margin: 10px;
    list-style: none;
    border:1px solid lightgray;
    border-radius: 10px;
    height: 100px;
    width: 300px;
    align-items: center;
    justify-content: center;
    filter: drop-shadow(0px 0px 10px black);
    transition: all 0.5s;
}

nav>ul>li:hover{
    filter: drop-shadow(0px 0px 30px black);
    cursor: pointer;
}

nav>ul>li>h3{
    margin: 5px 0 5px 0;
    font-size: 1.25em;
}

nav>ul>li>img{
    width:300px;
}

nav>ul>li>ul{
    padding: 15px;
}

nav>ul>li>ul>li{
    list-style: none;
    margin-bottom: 8px;
}
.click {
    background-color: white;
    display: flex;
    margin: 10px;
    list-style: none;
    border:1px solid lightgray;
    border-radius: 10px;
    height: 400px;
    width: 400px;
    align-items: center;
    justify-content: center;
    filter: drop-shadow(0px 0px 10px black);
    transition: all 0.5s;
}

.click:hover{
    filter: drop-shadow(0px 0px 30px black);
    cursor: pointer;
}

.click>img{
    width: 150px;
}

.img>h3{
    display: none;
}
.img>ul{
    display: none;
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
}
</style>