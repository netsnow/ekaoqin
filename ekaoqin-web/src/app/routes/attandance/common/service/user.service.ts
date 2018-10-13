import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class UserService {
    userSearchUrl = restUrl.root + restUrl.userSearch
    userUrl = restUrl.root + restUrl.user
    userMeUrl = restUrl.root + restUrl.me
    userRolesUrl = restUrl.root + restUrl.meRoles
    validataPassowrdUrl = restUrl.root + restUrl.validatePassword
    changePassowrdUrl = restUrl.root + restUrl.changePassword

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getAllUsers() {
        return this.http.get(this.userUrl)
    }
    getUsers(searchInfo, page, size) {
        var condition = {};
        if (searchInfo.username != "") {
            condition["fullname"] = searchInfo.username;
        }
        if (searchInfo.enabled != "") {
            condition["enabled"] = searchInfo.enabled;
        }
        console.log(this.userSearchUrl + "?page=" + page + "&size=" + size, condition)
        return this.http.post(this.userSearchUrl + "?page=" + page + "&size=" + size, condition)
    }
    getUserByUserName(username) {
        var condition = {};
        condition["username"] = username;
        return this.http.post(this.userSearchUrl, condition)
    }
    getUserByUserId(id) {
        var condition = {};
        condition["id"] = id;
        return this.http.get(this.userUrl + "/" + id)
    }
    getMe() {
        return this.http.get(this.userMeUrl)
    }
    getMyRoles() {
        return this.http.get(this.userRolesUrl)
    }
    saveUser(userInfo) {
        console.log(userInfo)
        return this.http.post(this.userUrl, userInfo);

        //表单里存在userid时为编辑，不存在时为新增
        //if (userInfo.hasOwnProperty("id")) {
        //    userInfo["id"] = userForm.get("id").value;
        //    return this.http.put(this.userUrl + "/" + userInfo["id"], userInfo);
        //} else {
        //    return this.http.post(this.userUrl, userInfo);
        //}
    }
    editUser(id, userInfo) {
        console.log(userInfo)
        return this.http.put(this.userUrl + '/' + id, userInfo);

        //表单里存在userid时为编辑，不存在时为新增
        //if (userInfo.hasOwnProperty("id")) {
        //    userInfo["id"] = userForm.get("id").value;
        //    return this.http.put(this.userUrl + "/" + userInfo["id"], userInfo);
        //} else {
        //    return this.http.post(this.userUrl, userInfo);
        //}
    }
    resetPassword(userId, passowrd) {
        var userInfo = {};
        userInfo["id"] = userId;
        userInfo["password"] = passowrd;
        return this.http.put(this.userUrl + "/" + userInfo["id"], userInfo);

    }
    remove(userId: string) {
        return this.http.delete(this.userUrl + "/" + userId);
    }
    enable(user: object) {
        user["enabled"] = !user["enabled"]
        user["password"] = null
        return this.http.put(this.userUrl + "/" + user["id"], user);
    }
    validatePassowrd(password) {
        return this.http.post(this.validataPassowrdUrl, password)
    }
    changePassword(passowrd) {
        console.log(passowrd);
        return this.http.post(this.changePassowrdUrl, passowrd)

    }

}