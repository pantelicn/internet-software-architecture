import axios from 'axios'
import { store } from '../store/store.js'

export default function interceptorsSetup(){
    axios.interceptors.request.use(function(config){
        const jwt = store.state.auth.jwtToken
        if(jwt){
            config.headers['Authorization'] = 'Bearer ' + jwt
        }
        return config
    })
}