<template>
    <main>
      <div>
        <h1>CHARACTERS</h1>
        <p>
          The major cultures of Roshar include the Alethi, a warlike and honor-bound 
          society; the scholarly and devout Vorin, who hold to a strict caste system 
          based on eye color; and the mysterious and reclusive Shin, who have a deep 
          respect for stone and maintain ancient traditions. Additionally, the Parshendi, 
          a race with ties to the ancient past, play a pivotal role in the series, adding layers of 
          complexity to the unfolding narrative.
        </p>
      </div>
      <nav>
        <ul id="orders">
          <li v-for="character in characters" :key="character.characterId" v-on:click="viewCharacterModal(character)" @click="toggle">
            <img :src="character.image" alt="">
            <h3>{{ character.name }}</h3>
            <p>From: {{ character.nationality }}</p>
          </li>  
        </ul>
        <!-- view character card -->
        <dialog ref="viewCharacterRef" v-show="isModalVisible" id="dialog-character">
          <section>
          <h2 v-on:click="closeCard()" id="closeCard" @click="toggle" >X</h2>
          <font-awesome-icon icon="fa-solid fa-pencil" v-if="$store.getters.isAuthenticated" id="closeCard" @click="updateCharacterModal()" v-on:click="toggleUpdate"></font-awesome-icon>
          <font-awesome-icon icon="fa-solid fa-trash-can" v-if="$store.getters.isAuthenticated" id="closeCard" @click="deleteCharacter()" v-on:click="toggle"></font-awesome-icon>
        </section>
        <img :src="selectedCharacter.image" alt="">
        <h2>{{ selectedCharacter.name }}</h2>
        </dialog>
      </nav>
      <!-- update character card -->
      <dialog ref="viewUpdateCharacterRef" id="dialog-update" v-show="updateModalVisible">
        <h2 @click="closeUpdateCard()" id="closeCard" v-on:click="toggleUpdate">X</h2>  
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedCharacter.name"></textarea>
        </div>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedCharacter.nationality"></textarea>
        </div>
        <div class="action-btns updateDialog">
          <button v-on:click="viewUpdateCard.close()" @click="toggleUpdate">Cancel</button>
          <button type="submit" v-on:click="updateCharacterRequest" @click="toggleUpdate">Update</button>
        </div>
      </dialog>
      <!-- New character "BUTTON" -->
      <section @click="newCharacterModal()" v-if="$store.getters.isAuthenticated">
        <font-awesome-icon icon="fa-solid fa-plus" id="plus"/>
        <H2>ADD NEW CHARACTER</H2>
      </section>
      <!-- New character Modal -->
      <dialog ref="viewNewCharacterRef" id="dialog-new">
        <h2 @click="newCharacterCard.close()" id="closeCard">X</h2>  
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="newCharacter.name" placeholder="Name"></textarea>
        </div>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="newCharacter.nationality" placeholder="Nationality"></textarea>
        </div>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="newCharacter.image" placeholder="Image Url"></textarea>
        </div>
        <div class="action-btns updateDialog">
          <button v-on:click="newCharacterCard.close()" >Cancel</button>
          <button type="submit" v-on:click="createCharacter">Add</button>
        </div>
      </dialog>
    </main>
</template>

<script>
import StormlightService from '../services/StormlightService';
import utility from '../utility/utility';

export default {
  data(){
    return {
      characters: [],
      viewCard: null,
      viewUpdateCard: null,
      selectedCharacter: {},
      isModalVisible: false,
      updateCharacter: {},
      updateModalVisible: false,
      newCharacter: {
        gender: "",
        locationId: 1 
      },
      newCharacterCard: null,
    }
  },
  methods: {
    // API Requests
    getCharacters() {
      StormlightService
      .getCharacters()
      .then((response) =>{
        this.characters = response.data;
      })
      .catch((error) => {
      utility.handleAxiosError(error, "Get characters");
    });
    },
    updateCharacterRequest(){
      StormlightService
      .updateCharacter(this.selectedCharacter)
      .then((response) =>{
        window.alert(response.status);
      })
      .catch((error) => {
        utility.handleAxiosError(error, "Update character");
      })
      .finally(() => this.viewUpdateCard.close());
    },
    deleteCharacter(){
      StormlightService
      .deleteCharacterById(this.selectedCharacter.characterId)
      .then((response) => {
        window.alert(response.status);
        if(response.status === 204){
          this.$router.push("/characters")
        }
      })
      .catch((error) => {
        utility.handleAxiosError(error, "Delete character");
      })
      .finally(() => this.viewCard.close());
    },
    createCharacter(){
      StormlightService
      .addCharacter(this.newCharacter)
      .then((response) => {
        window.alert(response.status);
      })
      .catch((error) => {
        utility.handleAxiosError(error, "Add character");
      })
      .finally(() => this.newCharacterCard.close());
    },

    // Modal methods
    viewCharacterModal(character){
      this.selectedCharacter = character;
      this.viewCard.showModal();
    },
    updateCharacterModal(){
      this.viewUpdateCard.showModal();
    },
    newCharacterModal(){
      this.newCharacterCard.showModal();
    },
    closeUpdateCard(){
      this.viewUpdateCard.close();
    },
    closeCard() {
      this.viewCard.close();
    },
    toggle(){
      this.isModalVisible=!this.isModalVisible
    },
    toggleUpdate(){
      this.updateModalVisible=!this.updateModalVisible
    }
  },
  created() {
    this.getCharacters();
  },
  mounted() {
    this.viewCard = this.$refs["viewCharacterRef"];
    this.viewUpdateCard = this.$refs['viewUpdateCharacterRef'];
    this.newCharacterCard = this.$refs['viewNewCharacterRef'];
  },
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

main>nav>ul>li{
    background-color: white;
    display: flex;
    flex-direction: column;
    margin: 10px;
    list-style: none;
    border:1px solid lightgray;
    border-radius: 10px;
    height: 100px;
    width: 400px;
    align-items: flex-start;
    justify-content: center;
    filter: drop-shadow(0px 0px 10px black);
    transition: all 0.5s;
    padding-left: 10px;

    >img{
      display: none;
    }

    >p{
      display: none;
    }
}

.updateDialog{
  padding-left: 0;
}

nav>ul>li:hover{
    filter: drop-shadow(0px 0px 30px black);
    cursor: pointer;
    display: flex;
    height: 100px;

    >p{
      display: contents;
    }

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

.img>h3{
    display: none;
}
.img>ul{
    display: none;
}

#dialog-character {
  z-index: 98;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 20px;

  > img {
    width: 300px;
  }

  > h3 {
    margin: 10px;
  }

  >section{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  #closeCard{
    margin-right: 20px;
    margin-left: 20px;
  }
}

#pencil:hover{
  cursor: pointer;
}

#closeCard:hover {
  cursor: pointer;
}

#dialog-update{
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 20px;
}

#dialog-new{
  flex-direction: column;
  align-items: center;
  border-radius: 20px;

  >div>textarea{
    margin-bottom: 10px;
  }

  >div>button{
    margin-bottom: 10px;
  }
}
.updateDialog{
  margin:0;
}

section{
  display: flex;
  justify-content: center;
  align-items: center;

  :hover{
    cursor: pointer;
  }
}

#plus{
  margin-right: 10px;
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