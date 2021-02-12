<template>
    <b-container>
        <b-row align-h="center" class="mt-5 mb-5">
                <h2> My Profile </h2>
        </b-row>
        <b-row v-if="personInfo != null">
            <b-col>
                <b-row>
                    <label for="pid"> Uniqe person identifier:</label>
                </b-row>
                <b-row cols-sm="7">
                    <b-form-input id="pid" v-model="personInfo.pid" type="text" placeholder="Enter a new PID" required
                    :disabled="!editing">
                    </b-form-input>
                </b-row>
                <b-row align-h="start">
                    <b-form-invalid-feedback :state="pidValid">
                        Please enter a valid pid
                    </b-form-invalid-feedback>
                </b-row>

                <b-row class="mt-4">
                    <label for="name"> Your name: </label>
                </b-row>
                <b-row cols-sm="10" align-h="start">
                    <b-col class="p-0">
                        <b-form-input id="first-name" v-model="personInfo.firstName" type="text"
                            placeholder="Enter your first name" required :disabled="!editing">
                        </b-form-input>
                        <b-form-invalid-feedback :state="firstNameValid">
                            Your first name must be at least 3 characters long
                        </b-form-invalid-feedback>
                    </b-col>
                    <b-col>
                        <b-form-input id="last-name" v-model="personInfo.lastName" type="text"
                            placeholder="Enter your last name" required :disabled="!editing"></b-form-input>
                        <b-form-invalid-feedback :state="lastNameValid">
                            Your last name must be at least 3 characters long
                        </b-form-invalid-feedback>
                    
                    </b-col>
                </b-row>
                <b-row class="mt-4">
                    <label for="gender"> Gender: </label>
                </b-row>
                <b-row cols-sm="7">
                    <b-form-select v-model="personInfo.gender" :options="genderOptions" :disabled="!editing"></b-form-select>
                </b-row>
                <b-row>
                    <b-form-invalid-feedback :state="genderValid">
                            Please select a gender
                    </b-form-invalid-feedback>
                </b-row>

                <b-row>
                    <label for="phone-number"> Phone number:</label>
                </b-row>
                <b-row cols-sm="7">
                    <b-form-input id="phone-number" v-model="personInfo.phoneNumber" type="text"
                     placeholder="Enter a new phone number" required :disabled="!editing">
                    </b-form-input>
                </b-row>
                <b-row>
                    <b-form-invalid-feedback :state="phoneNumberValid">
                            Please enter a valid phone number
                    </b-form-invalid-feedback>
                </b-row>

                <b-row class="mt-4">
                    <label for="dateofbirth"> Date of birth: </label>
                </b-row>
                <b-row cols="7">
                    <b-form-datepicker id="dateofbirth" v-model="personInfo.dateOfBirth" :max="maxDate"
                    :disabled="!editing">
                    </b-form-datepicker>
                </b-row>
                <b-row>
                    <b-col>
                        <b-row class="mt-4">
                            <label for="address"> Address: </label>
                        </b-row>
                        <b-row cols="7">
                            <VueGoogleAutocomplete v-model="getAddress" ref="address" id="map" classname="form-control" v-if="editing"
                                placeholder="Please type your address" v-on:placechanged="getAddressData">
                            </VueGoogleAutocomplete>
                            <b-form-input id="address" :value="getAddress" type="text" v-else
                                    placeholder="Enter your first name" required disabled></b-form-input>
                        </b-row>
                        <b-row>
                            <b-form-invalid-feedback :state="addressValid">
                                    Please insert a valid address
                            </b-form-invalid-feedback>
                        </b-row>
                    </b-col>
                    <b-col class="ml-2">
                         <b-row class="mt-4">
                            <label for="zip-code"> Zip code: </label>
                        </b-row>
                        <b-row>
                            <b-form-input id="zip-code" v-model="personInfo.address.city.postalCode" type="text"
                            placeholder="Enter zip code" required :disabled="!editing">
                            </b-form-input>
                        </b-row>
                        <b-row>
                            <b-form-invalid-feedback :state="zipCodeValid">
                                    Please insert a valid zip code
                            </b-form-invalid-feedback>
                        </b-row>
                    </b-col>
                </b-row>
            </b-col>

            <b-col class="ml-5">
                <b-row>
                    <label for="email"> Email : </label>
                </b-row>
                <b-row cols="7">
                    <b-form-input id="email" v-model="personInfo.email" type="text" placeholder="Enter an email"
                    disabled>
                    </b-form-input>
                </b-row>
                <b-row class="mt-4">
                    <label for="email"> Username : </label>
                </b-row>
                <b-row cols="7">
                    <b-form-input id="username" v-model="personInfo.username" type="text" placeholder="Enter an username"
                    :disabled="!editing">
                    </b-form-input>
                </b-row>
                <b-row>
                    <b-form-invalid-feedback :state="userNameValid">
                            Your username must be at least 5 characters long 
                    </b-form-invalid-feedback>
                </b-row>
                <b-row class="mt-4">
                    <label for="password"> Password : </label>
                </b-row>
                <b-row cols="7">
                    <b-form-input id="password" v-model="personInfo.password" type="password" placeholder="Enter a password"
                    :disabled="!editing">
                    </b-form-input>
                </b-row>
                <b-row>
                    <b-form-invalid-feedback :state="passwordValid">
                            Your password must contain at least 5 characters
                    </b-form-invalid-feedback>
                </b-row>
                <b-row>
                    <b-form-invalid-feedback :state="passwordsMatching">
                            Passwords must match
                    </b-form-invalid-feedback>
                </b-row>
                
                <b-row class="mt-4" v-if="editing">
                    <label for="password"> Validate your password : </label>
                </b-row>
                <b-row cols="7" v-if="editing">
                    <b-form-input v-model="passwordValidated" type="password" placeholder="Validate your password"
                    :disabled="!editing">
                    </b-form-input>
                </b-row>
                <b-row>
                    <b-form-invalid-feedback :state="passwordsMatching">
                            Passwords must match
                    </b-form-invalid-feedback>
                </b-row>

            </b-col>
        </b-row>
        <b-row align-h="center" class="mt-3">
            <b-button @click="edit" v-if="!editing">Change your personal information</b-button>
            <b-button variant="danger" @click="cancel" v-if="editing">Cancel</b-button>
            <b-button variant="success" @click="submit" v-if="editing" class="ml-5" :disabled="!formValid">
                Confirm changes
            </b-button>
        </b-row>
    </b-container>
</template>
<script>

import VueGoogleAutocomplete from 'vue-google-autocomplete'
import axios from 'axios'
import { api } from '../../api.js'

function getMaxDate() {
    let now = new Date()
    now.setFullYear(now.getFullYear() - 18)
    return now
}

export default {
    name: 'Profile',
    components:{ VueGoogleAutocomplete },
    data() {
        return {
            personInfo: null,
            personInfoBackup:null,
            editing:false,
            passwordValidated:'',
            googleAddress: '',
            genderOptions: [
                { value: '', text: 'Select gender' },
                { value: 'MALE', text: 'Male' },
                { value: 'FEMALE', text: 'Female' }
            ],
            maxDate:getMaxDate(),
            
        }
    },
    computed:{
        getAddress: function(){
            return this.personInfo.address.streetName + ', ' + this.personInfo.address.city.name + ', '
            + this.personInfo.address.city.country.name
        },
        pidValid: function(){
            const regex = /^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$/
            return regex.test(this.personInfo.pid)
        },
        firstNameValid: function(){
            return this.personInfo.firstName.length > 2
        },
        lastNameValid: function (){
            return this.personInfo.lastName.length > 2
        },
        genderValid: function(){
            return this.personInfo.gender !== ''
        },
        phoneNumberValid: function(){
            return this.personInfo.phoneNumber.length > 5
        },
        addressValid: function(){
                return !this.personInfo.address.streetName.includes('undefined') 
                && this.personInfo.address.city.name !== undefined
                && this.personInfo.address.city.country.name !== undefined
        },
        userNameValid: function(){
            return this.personInfo.username.length > 4
        },
        passwordValid: function(){
            return this.personInfo.password.length > 5
        },
        passwordValidationValid: function(){
            return this.passwordValidated.length > 5
        },
        passwordsMatching: function (){
            return this.personInfo.password === this.passwordValidated
        },
        zipCodeValid:function(){
            return this.personInfo.address.city.postalCode.length > 4
        },
        formValid:function(){
            return this.pidValid && this.firstNameValid && this.lastNameValid && this.genderValid
            && this.phoneNumberValid && this.addressValid && this.userNameValid && 
            this.passwordValid && this.passwordValidationValid && this.passwordsMatching
            && this.zipCodeValid
        }
    },
    methods: {
        getAddressData: function (addressData, placeResultData, id) {
            this.googleAddress = addressData;
            this.personInfo.address.streetName = this.googleAddress.route +" " + this.googleAddress.street_number
            this.personInfo.address.latitude = this.googleAddress.latitude
            this.personInfo.address.longitude = this.googleAddress.longitude
            this.personInfo.address.city.name = this.googleAddress.locality
            this.personInfo.address.city.country.name = this.googleAddress.country
        },
        fetchPersonInfo:function(){
            axios.get(api.person.root)
            .then( res=>{
                this.personInfo = res.data
                this.passwordValidated = this.personInfo.password
            })
        },
        edit:function(){
            this.editing = !this.editing
            this.googleAddress = this.getAddress
            this.personInfoBackup = JSON.parse(JSON.stringify(this.personInfo)) 
        },
        cancel:function(){
            this.editing = !this.editing
            this.personInfo = JSON.parse(JSON.stringify(this.personInfoBackup))  
            this.passwordValidated = this.personInfo.password
        },
        submit:function(){
            this.editing = !this.editing
            this.postPersonData()

        },
        getPersonDataDto:function(){
            return {
                id: this.personInfo.id,
                pid: this.personInfo.pid,
                firstName: this.personInfo.firstName,
                lastName: this.personInfo.lastName,
                gender: this.personInfo.gender,
                phoneNumber: this.personInfo.phoneNumber,
                dateOfBirth: this.personInfo.dateOfBirth,
                address: this.personInfo.address
            }
        },
        getCredentialsDto:function(){
            return {
                username: this.personInfo.username,
                password: this.personInfo.password,
                email: this.personInfo.email
            }
        },
        postPersonData:function(){
            axios.put(api.person.root,this.getPersonDataDto())
            .then(res=>{
                this.postCredentials()
            })
            .catch(err=>{
                 this.$toast.error(err.response.data);
                 this.personInfo = this.personInfoBackup
            })
        },
        postCredentials:function(){
            axios.put(api.person.credentials,this.getCredentialsDto())
            .then(res=>{
                this.$toast.open('Your profile is successfully updated!')
            })
            .catch(err=>{
                this.$toast.error(err.response.data)
                this.personInfo = this.personInfoBackup
            })
        }
    },
    mounted() {
        this.fetchPersonInfo()
        
    },
                
}
</script>

<style scoped>
.col-xl-auto{
    padding-left: 0px !important;
}


</style>