<template>
  <main>
    <div>
      <h1>THE ORDERS OF THE KNIGHTS RADIANT</h1>
      <p>
        "Life before Death, Strength before Weakness, Journey before
        Destination" - FIRST IDEAL
      </p>
    </div>
    <nav>
      <ul id="orders">
        <li
          v-for="order in orders" :key="order.orderId" v-on:click="viewOrderModal(order)" @click="toggle">
          <img :src="order.glyph" />
          <h3>{{ order.name }}</h3>
        </li>
      </ul>
      <!-- view order card -->
      <dialog ref="viewOrderRef" v-show="isModalVisible">
        <section>
          <h2 v-on:click="closeCard()" id="closeCard" @click="toggle" >X</h2>
          <font-awesome-icon icon="fa-solid fa-pencil" v-if="$store.getters.isAuthenticated" id="pencil" @click="updateOrderModal()" v-on:click="toggleUpdate"></font-awesome-icon>
        </section>
        <img :src="selectedOrder.glyph" />
        <h2>{{ selectedOrder.name }}</h2>
        <h3>SECOND IDEAL: {{ selectedOrder.secondIdeal }}</h3>
        <h3>THIRD IDEAL: {{ selectedOrder.thirdIdeal }}</h3>
        <h3>FOURTH IDEAL: {{ selectedOrder.fourthIdeal }}</h3>
        <h3>FIFTH IDEAL: {{ selectedOrder.fifthIdeal }}</h3>
      </dialog>
      <dialog ref ="viewUpdateOrderRef" v-show="updateOrderVisible" id="dialog-update">
        <h2 @click="closeUpdateCard()" id="closeCard" v-on:click="toggleUpdate">X</h2>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedOrder.secondIdeal"></textarea>
        </div>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedOrder.thirdIdeal"></textarea>
        </div>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedOrder.fourthIdeal"></textarea>
        </div>
        <div class="updateDialog">
          <textarea name="" id="" cols="30" rows="1" v-model="selectedOrder.fifthIdeal"></textarea>
        </div>
        <div class="action-btns updateDialog">
          <button v-on:click="viewUpdateCard.close()" @click="toggleUpdate">Cancel</button>
          <button type="submit" v-on:click="updateOrderRequest" @click="toggleUpdate">Update</button>
        </div>
      </dialog>
    </nav>
  </main>
</template>

<script>
import StormlightService from "../services/StormlightService";
import utility from "../utility/utility";

export default {
  data() {
    return {
      orders: [],
      isModalVisible: false,
      viewCard: null,
      selectedOrder: {},
      updateOrderVisible: false,
      viewUpdateCard: null
    };
  },
  methods: {
    // API Requests
    getOrders() {
      StormlightService.getOrders().then((response) => {
        this.orders = response.data;
      })
      .catch((error) => {
        utility.handleAxiosError(error, "Get orders");
      });
    },
    updateOrderRequest(){
      StormlightService
      .updateOrder(this.selectedOrder)
      .then((response) => {
        window.alert(response.status);
      })
      .catch((error) => {
        utility.handleAxiosError(error, "Update order");
      })
      .finally(() => this.viewUpdateCard.close());
    },

    // Modal methods
    viewOrderModal(order) {
      console.log("testing");
      this.selectedOrder = order;
      this.viewCard.showModal();
    },
    updateOrderModal(){
      this.viewUpdateCard.showModal()
    },
    closeCard() {
      this.viewCard.close();
    },
    closeUpdateCard() {
      this.viewUpdateCard.close();
    },
    toggle(){
      this.isModalVisible=!this.isModalVisible;
    },
    toggleUpdate(){
      this.updateOrderVisible = !this.updateOrderVisible;
    }
  },
  created() {
    this.getOrders();
  },
  mounted() {
    this.viewCard = this.$refs["viewOrderRef"];
    this.viewUpdateCard = this.$refs['viewUpdateOrderRef'];
  },
};
</script>

<style scoped>
main {
  display: flex;
  flex-direction: column;
  width: 100%;
  grid-area: main;
}

h1 {
  font-size: 48px;
  margin-bottom: 5px;
}

div {
  display: flex;
  flex-direction: column;
  padding-left: 10px;
  margin-left: 50px;
}

main > nav > ul {
  display: flex;
  flex-wrap: wrap;
  padding-left: 50px;
  justify-content: center;
}

main > nav > ul > li {
  background-color: white;
  display: flex;
  flex-direction: column;
  margin: 10px;
  list-style: none;
  border: 1px solid lightgray;
  border-radius: 10px;
  height: 400px;
  width: 400px;
  align-items: center;
  justify-content: center;
  filter: drop-shadow(0px 0px 10px black);
  transition: all 0.5s;

  > h3 {
    display: none;
  }

  > ul {
    display: none;
  }
}

nav > ul > li:hover {
  filter: drop-shadow(0px 0px 30px black);
  cursor: pointer;

  > h3 {
    display: contents;
  }
}

nav > ul > li > h3 {
  margin: 5px 0 5px 0;
  font-size: 1.25em;
}

nav > ul > li > img {
  width: 300px;
}

nav > ul > li > ul {
  padding: 15px;
}

nav > ul > li > ul > li {
  list-style: none;
  margin-bottom: 8px;
}

.img > h3 {
  display: none;
}
.img > ul {
  display: none;
}

dialog {
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

  >div{
    margin-left: 0;
    margin-top: 10px;
    padding: 0;

    >textarea{
      width: 250px;
    }

    >button{
      margin-bottom: 10px;
      width: 250px;
  }
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
