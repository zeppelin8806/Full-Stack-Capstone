import axios from 'axios';

export default {
    // Order API
    getOrders() {
        return axios.get('/orders');
    },
    getOrderById(orderId){
        return axios.get(`/orders/${orderId}`);
    },
    updateOrder(order){
        return axios.put(`/orders/${order.orderId}`, order);
    },
    // Shards API
    getShards() {
        return axios.get('/shards');
    },
    getShardsById(shardsId){
        return axios.get(`/shards/${shardsId}`);
    },
    addShards(shard){
        return axios.post('/shards', shard);
    },
    updateShard(shard){
        return axios.put(`/shards/${shard.shardbladeId}`, shard)
    },
    deleteShardById(shardId){
        return axios.delete(`/shards/${shardId}`);
    },
    // Location API
    getLocation(){
        return axios.get('/location');
    },
    // Character API
    getCharacters(){
        return axios.get('/characters');
    },
    updateCharacter(character){
        return axios.put(`/characters/${character.characterId}`, character);
    },
    deleteCharacterById(characterId){
        return axios.delete(`/characters/${characterId}`);
    },
    addCharacter(character){
        return axios.post('/characters', character)
    }

}