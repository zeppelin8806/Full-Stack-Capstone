<template>
   <header id="app-header">
    <img
        src="../../public/images/stormlight-original_April-7-2020.webp"
        alt="stormfather"
        id="banner"
      />
   </header>
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
header{
    display: flex;
    justify-content: center;
    background-color: rgb(7, 16, 45);
}

#banner{
    width: 50%;
    height: 250px;
    object-fit: cover;
    object-position: 100% 45%;
}

@media only screen and (max-width: 426px){
    #banner{
        max-height: 200px;
        object-position: 50% 100%;
    }
}

</style>