import jwt_decode from 'jwt-decode';
import axios from 'axios';
import { api } from '../../api.js'

const authModule ={
    state:{
        jwtToken: null,
        loggedPerson: null,
        loggedEmployeeId: null,
    },
    mutations:{
        setJwt(state,payload){
            state.jwtToken = payload
        },
        setLoggedPerson(state){
            var decoded = jwt_decode(state.jwtToken)
            state.loggedPerson = JSON.parse(decoded.identity)
        },
        fetchLoggedEmployeeId(state){
            axios.get(api.employees.employeeId + state.loggedPerson.userId)
            .then(res=>{
                state.loggedUserId = res.data
            })
        }
    }
}
export default authModule