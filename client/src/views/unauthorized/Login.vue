<template>
    <b-container>
        <b-row align-h="center" class="mt-4">
            <h2>
                Login
            </h2>
        </b-row>

        <b-row align-h="center" class="mt-4">
            <b-col cols="5" class="mt-4">
                <b-row>
                    <label for="email">Email address:</label>
                </b-row>
                <b-row>
                    <b-form-input
                        id="email"
                        placeholder="Enter email"
                        v-model="email"
                        :state="emailValid"
                    >
                    </b-form-input>
                </b-row>
                    
                <b-row align-h="start">
                    <b-form-invalid-feedback :state="emailValid">
                        Please enter a valid email
                    </b-form-invalid-feedback>
                </b-row>

                <b-row class="mt-4">
                    <label for="password">Password: </label>
                </b-row>
                <b-row>
                    <b-form-input type="password" id="text-password" placeholder="Enter password"
                    v-model="password" :state="passwordValid"
                    >
                    </b-form-input>
                </b-row>
                     <b-form-invalid-feedback :state="passwordValid">
                        Please enter a password
                    </b-form-invalid-feedback>
                <b-row>
                </b-row>
                <b-row align-h="center" class="mt-4" >
                    <b-button :disabled="!(emailValid && passwordValid)" squared size="lg"
                    @click="login">
                        LOGIN
                    </b-button>
                </b-row>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import axios from 'axios'
import { api } from '../../api.js'
import { setJwt,getRole } from '../../helpers/jwt.js'
export default {
    name: 'Login',
    computed:{
        emailValid(){
            const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return regex.test(this.email)
        },
        passwordValid(){
            return this.password.length > 0
        }
    },
    data() {
        return {
            email:'',
            password:''
        }
    },
    methods:{
        reroute(){
            let role = getRole()
            if(role === "ROLE_PHARMACIST"){
                this.$router.push('/pharmacist/')
            }
            else if(role === "ROLE_DERMATOLOGIST"){
                this.$router.push('/dermatologist/')
            }
            else if(role === "ROLE_PATIENT") {
                this.$router.push('/patient')
            }
            else if(role === "ROLE_SUPPLIER") {
              this.$router.push("/supplier");
            }
            else if (role === "ROLE_SYS_ADMIN") {
              this.$router.push("/sys");
            }
        },
        clearInput(){
            this.email = ''
            this.password = ''
        },
        login(){
            let credentials = {
                email: this.email,
                password: this.password
            }
            axios.post(api.auth.login,credentials).then(res => {
                setJwt(res.headers['authorization'])
                this.reroute()
            })
            .catch(err => {
                if (err.response.status === 401) {
                  this.$toast.error("User does not exist or is not activated.")
                  this.clearInput()
                }
                else if (err.response.status === 500) {
                    this.$toast.error("Server error occurred.");
                }
                else {
                    this.$toast.error("An error occurred");
                }
            })
        }
    }
}
</script>

<style>

</style>