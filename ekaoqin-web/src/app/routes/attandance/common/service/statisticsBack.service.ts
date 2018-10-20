import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class StatisticsBackService {
    url = restUrl.root + restUrl.statisticsBack

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getAllByDate(date) {
        return this.http.get(this.url + "/" + date)
    }
    getClaxxByDate(date) {
        var selectDate = date.toJSON().substring(0,10)
        return this.http.get(this.url + "/" + selectDate + "/claxx")
    }
    getRoomByDate(date) {
        var selectDate = date.toJSON().substring(0,10)
        return this.http.get(this.url + "/" + selectDate + "/room")
    }



}