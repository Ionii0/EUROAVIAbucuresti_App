import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CommentPayload} from "./comment-payload.payload";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private httpClient:HttpClient) { }

  getAllCommentsForPost(postId:number):Observable<any>{
    return this.httpClient.get('http://localhost:8080/api/comments/forAnnouncement/'+postId);
  }
  postComment(commentPayload:CommentPayload):Observable<any>{
    return this.httpClient.post('http://localhost:8080/api/comments/',commentPayload);
  }
}
