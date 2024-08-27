<template>
    <aside id="aside-left">
      <nav id="nav-desktop">
        <router-link class="nav-button" :to="{name: 'home'}">HOME</router-link>
        <router-link class="nav-button" :to="{name: 'characters'}">CHARACTERS</router-link>
        <router-link class="nav-button" :to="{name: 'shards'}">SHARDS</router-link>
        <router-link class="nav-button" :to="{name: 'orders'}">KNIGHTS RADIANT</router-link>
        <router-link class="nav-button" :to="{name: 'location'}">LOCATION</router-link>
      </nav>
      <section id="nav-desktop">
            <h3 id="signup">SIGN UP FOR OUR WEEKLY NEWSLETTER</h3>
            <textarea name="" id="" cols="30" rows="1" placeholder="EMAIL"></textarea>
            <button type="submit" >SUBMIT</button>
        </section>
      <nav id="nav-mobile" class="hamburger-menu">
        <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
        />
        <i class="fa-solid fa-bars" @click="showModal()"></i>
        <dialog ref="viewMobile">
          <div class="dialog-menu">
            <i class="fa-solid fa-xmark" @click="closeModal()"></i>
            <router-link class="nav-button" :to="{name: 'home'}">HOME</router-link>
            <router-link class="nav-button" :to="{name: 'characters'}">CHARACTERS</router-link>
            <router-link class="nav-button" :to="{name: 'shards'}">SHARDS</router-link>
            <router-link class="nav-button" :to="{name: 'orders'}">KNIGHTS RADIANT</router-link>
            <router-link class="nav-button" :to="{name: 'location'}">LOCATION</router-link>
            
          </div>
          <section >
            <h3 id="signup">SIGN UP FOR OUR WEEKLY NEWSLETTER</h3>
          <textarea ref="emailForm" name="email" id="email-mobile" cols="30" rows="1" v-model="email" placeholder="EMAIL"></textarea>
          <button type="submit" @click.prevent="addEmail">SUBMIT</button>
        </section>
        </dialog>
      </nav>
      
    </aside>
</template>

<script>
    export default {
        data(){
            return{
                email: '',
                viewNav: null,
                emailSub: null
            }
        },
        methods:{
            showModal(){
                this.viewNav.showModal();
            },
            closeModal(){
                this.viewNav.close();
            },
            addEmail(){
                console.log('Email submitted:', this.email);
                this.email = '';
            }
        },
        mounted() {
            this.viewNav = this.$refs['viewMobile'];
            this.emailSub = this.$refs['emailForm'];
        }
    }
</script>

<style scoped>
#aside-left{

    display: flex;
    flex-direction: column;
    justify-content: space-between;
    
    background-color: rgb(21, 30, 59);

    &>nav{
        justify-content: center;
    }

    .nav-button{
        display: flex;
        flex-direction: column;
        align-items: center;
        text-decoration: none;
        padding: 0;
        font-size: 18px;
        margin-top: 20px;
        color:aliceblue
    }

}


#nav-mobile{
    display: none;
    height: 1rem;

}

dialog::backdrop{
    display: flex;
    flex-direction: column;

    & > i{
        text-align: right;
    }
}

section{
    display:flex;
    flex-direction: column;
    align-items: center;
    color:aliceblue;
    flex-wrap: wrap;
    justify-content: flex-end;
    margin-bottom: 20px;

    >#signup{
        text-align: center;
        
    }
    >button, textarea{
        width:200px;
    }
}

@media only screen and (max-width: 426px){
    #aside-left{
        display: flex;
        justify-content: center;
        align-items: flex-end;

        .fa-solid.fa-bars{
            padding-right: 10px;
        }
        .fa-solid.fa-bars::before{
            padding-right: 10px;
        }
    }


    #nav-desktop{
        display: none;
    }
    section{
            color: rgb(21, 30, 59);
        }

    #nav-mobile{
        display:flex;

        .nav-button{
            color: rgb(21, 30, 59);
        }

        &>i{
            justify-self: center;
            align-self: center;
        }
        
    }
    
    i[class="fa-solid fa-bars"] {
        color: white;
    }

}

</style>