import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class RoomService {
    roomUrl = restUrl.root + restUrl.room

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getAllRooms() {
        return this.http.get(this.roomUrl)
    }
    saveRoom(roomInfo) {
        return this.http.post(this.roomUrl, roomInfo);
    }
    editRoom(id, roomInfo) {
        return this.http.put(this.roomUrl + '/' + id, roomInfo);
    }
    remove(id: string) {
        return this.http.delete(this.roomUrl + "/" + id);
    }


}