export class CommentPayload{
    announcementId:number;
    body:string;
    mailEuroavia:string;


  constructor(announcementId: number, body: string, mailEuroavia: string) {
    this.announcementId = announcementId;
    this.body = body;
    this.mailEuroavia = mailEuroavia;
  }
}
