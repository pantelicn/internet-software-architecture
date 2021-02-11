
import jwt_decode from 'jwt-decode';

function setJwt(jwtToken){
    localStorage.setItem('jwtToken', jwtToken) 
}
function getJwt(){
    return localStorage.getItem('jwtToken')
}
function clearJwt(){
    localStorage.removeItem('jwtToken')
}
function getRole(){
    if(getJwt() == null)
        return null

    var decoded = jwt_decode(getJwt())
    let loggedPerson = JSON.parse(decoded.identity)
    return loggedPerson.role
}
function getRoleId(){
    if(getJwt() == null)
        return null

    var decoded = jwt_decode(getJwt())
    let loggedPerson = JSON.parse(decoded.identity)
    return loggedPerson.roleId
}
function isAuthenticated(){
    return localStorage.getItem('jwtToken') != null
}

export { setJwt, getJwt,getRole, isAuthenticated,clearJwt, getRoleId }