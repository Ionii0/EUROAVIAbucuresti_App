export class ParticipatePayload{
    announcementId:number;
    mailEuroavia:string;


  constructor(announcementId: number, mailEuroavia: string) {
    this.announcementId = announcementId;
    this.mailEuroavia = mailEuroavia;
  }
}
