import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Video} from "../entity/video";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  createVideo(flightId: number, video: Video): Observable<Video> {
    return this.http.post<Video>(environment.apiUrl + `/flights/${flightId}/video`, video, this.httpOptions);
  }

  readVideo(flightId: number): Observable<Video> {
    return this.http.get<Video>(environment.apiUrl + `/flights/${flightId}/video`);
  }

  updateVideo(flightId: number, video: Video): Observable<Video> {
    return this.http.put<Video>(environment.apiUrl + `/flights/${flightId}/${video.id}`, video, this.httpOptions);
  }

  deleteVideo(flightId: number, videoId: number): Observable<void> {
    return this.http.delete<void>(environment.apiUrl + `/flights/${flightId}/video/${videoId}`);
  }
}
