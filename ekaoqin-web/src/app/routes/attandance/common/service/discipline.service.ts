import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class DisciplineService {
    url = restUrl.root + restUrl.discipline

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getAllDisciplines() {
        return this.http.get(this.url)
    }
    saveDiscipline(disciplineInfo) {
        return this.http.post(this.url, disciplineInfo);
    }


}