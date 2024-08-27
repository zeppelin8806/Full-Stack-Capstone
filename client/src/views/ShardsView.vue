<template>
    <main>
      <div>
        <h1>SHARDS</h1>
        <p>
          Legendary weapons of immense power in The Stormlight Archive, 
          revered and feared across the world of Roshar. These mystical 
          swords are not mere tools of war; they are deeply tied to the 
          ancient orders of the Knights Radiant and the enigmatic Spren who inhabit the world.
        </p>
      </div>
      <nav>
        <ul id="orders">
          <li v-for="shard in shards" :key="shard.shardbladeId" class="orderCards">
            <div id="orderInfo">
              <h3>{{ shard.shardbladeName }}</h3>
              <p>{{ shard.shardbladeType }}</p>
            </div>
            <div id="editIcons">
              <font-awesome-icon icon="fa-solid fa-pencil" v-if="$store.getters.isAuthenticated" id="closeCard" @click="updateShardModal(selectedShard)" v-on:click="selectedShard=shard"></font-awesome-icon>
              <font-awesome-icon icon="fa-solid fa-trash-can" v-if="$store.getters.isAuthenticated" id="closeCard" @click="deleteShard(shard.shardbladeId)"></font-awesome-icon>
            </div>
          </li> 
        </ul>
      </nav>
      <dialog ref="viewUpdateShardRef">
        <h2 @click="newCharacterCard.close()" id="closeCard">X</h2>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedShard.shardbladeName" placeholder="Shardblade Name"></textarea>
        </div>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedShard.shardbladeType" placeholder="Shardblade Type"></textarea>
        </div>
        <div class="action-btns updateDialog">
          <button v-on:click="viewUpdateModal.close()">Cancel</button>
          <button type="submit" v-on:click="updateShard">Update</button>
        </div>
      </dialog>
      <!-- New shard "button" -->
      <section @click="newShardModal()" v-if="$store.getters.isAuthenticated">
        <font-awesome-icon icon="fa-solid fa-plus" id="plus"/>
        <H2>ADD NEW SHARD</H2>
      </section>
      <!-- New shard modal -->
      <dialog ref="viewNewShardRef" id="dialog-new">
        <h2 @click="viewNewCard.close()" id="closeCard">X</h2>
        <div >
          <textarea name="" id="" cols="30" rows="1" v-model="newShard.shardbladeName" placeholder="Shardblade's Name"></textarea>
        </div>
        <div >
          <textarea name="" id="" cols="30" rows="1" v-model="newShard.shardbladeType" placeholder="Type of Shardblade"></textarea>
        </div>
        <div>
          <button v-on:click="viewNewCard.close()" >Cancel</button>
          <button type="submit" v-on:click="createShard">Add</button>
        </div>        
      </dialog>
    </main>
</template>

<script>
import StormlightService from '../services/StormlightService';
import utility from '../utility/utility';

export default {
  data () {
    return {
      shards: [],
      viewNewCard: null,
      newShard: {
        characterId: 1
      },
      selectedShard: {
        characterId: 1
      }
    }
  },
  methods: {
    getShards() {
      StormlightService
      .getShards()
      .then((response) => {
        this.shards = response.data;
      })
      .catch(error => {
        utility.handleAxiosError(error, "Get shards");
      })
    },
    createShard(){
      StormlightService
      .addShards(this.newShard)
      .then ((response) => {
        window.alert(response.status);
      })
      .catch(error => {
        utility.handleAxiosError(error, "Add shard");
      })
      .finally(() => this.viewNewCard.close());
    },
    updateShard(){
      StormlightService
      .updateShard(this.selectedShard)
      .then((response) => {
        window.alert(response.status);
      })
      .catch((error) => {
        utility.handleAxiosError(error, "Update shard");
      })
      .finally(() => this.viewUpdateCard.close());
    },
    deleteShard(shardId){
      StormlightService
      .deleteShardById(shardId)
      .then((response) => {
        window.alert(response.status);
      })
      .catch((error) => {
        utility.handleAxiosError(error, "Delete shard");
      })
      .finally(() => this.$router.push("/shards"));
    },
    updateShardModal(){
      this.viewUpdateModal.showModal();
    },
    newShardModal(){
      this.viewNewCard.showModal();
    }
  },
  created() {
    this.getShards();
  },
  mounted(){
    this.viewNewCard = this.$refs['viewNewShardRef'];
    this.viewUpdateModal = this.$refs['viewUpdateShardRef'];
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
    justify-content: flex-start;
}

.orderCards{
    background-color: white;
    display: flex;
    flex-direction: row;
    margin: 10px;
    list-style: none;
    border:1px solid lightgray;
    border-radius: 10px;
    height: 100px;
    width: 400px;
    align-items: center;
    justify-content: space-between;
    filter: drop-shadow(0px 0px 10px black);
    transition: all 0.5s;
    padding-left: 10px;
}

.orderCards:hover{
    filter: drop-shadow(0px 0px 30px black);

    >#editIcons{
      display: flex;
      flex-direction: row;
      margin-right: 10px;
      
      >#closeCard{
        cursor: pointer;
      }
    }
}

#editIcons{
  display: none;
  margin-right: 10px;
}

#closeCard{
  margin-left: 10px;
}

#orderInfo{
  margin:0;

  >h3{
    margin-top: 5px;
    margin-bottom: 5px;
  }

  >p{
    margin-top: 5px;
    margin-bottom: 5px;
  }
}
.orderCards>h3{
    margin: 5px 0 5px 0;
    font-size: 1.25em;
}

.orderCards>img{
    width:300px;
}

.orderCards>ul{
    padding: 15px;
}

nav>ul>li>ul>li{
    list-style: none;
    margin-bottom: 8px;
}

.img>h3{
    display: none;
}
.img>ul{
    display: none;
}

section{
  display: flex;
  justify-content: center;
  align-items: center;

  :hover{
    cursor: pointer;
  }
}

#dialog-new{
  flex-direction: column;
  align-items: center;
  border-radius: 20px;

  >div{
    margin: 0;
  }

  >div>textarea{
    margin-bottom: 10px;
  }

  >div>button{
    margin-bottom: 10px;
  }

  >h2:hover{
    cursor: pointer;
  }
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