import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentPayload } from './comment-payload.payload';

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
