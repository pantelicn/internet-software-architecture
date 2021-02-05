import jwt_decode from "jwt-decode";

const authModule ={
    state:{
        jwtToken: null,
        loggedPerson: null,
    },
    mutations:{
        setJwt(state,payload){
            state.jwtToken = payload
        },
        setLoggedPerson(state){
            var decoded = jwt_decode(state.jwtToken)
            state.loggedPerson = JSON.parse(decoded.identity)
        }
    }
}
export default authModule