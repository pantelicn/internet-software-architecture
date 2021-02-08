import axios from 'axios'
import { getJwt } from './jwt.js'

export default function interceptorsSetup(){
    axios.interceptors.request.use(function(config){
        const jwt = getJwt()
        if(jwt){
            config.headers['Authorization'] = 'Bearer ' + jwt
        }
        return config
    })
}