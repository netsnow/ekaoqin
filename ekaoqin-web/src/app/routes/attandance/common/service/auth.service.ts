import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant'

@Injectable({
    providedIn: 'root',
})
export class AuthService {


    authUrl = restUrl.root + restUrl.auth;

    constructor(
        private http: HttpClient,
    ) { };


    login(logininfo) {
        return this.http.post(this.authUrl, logininfo)

    }
}