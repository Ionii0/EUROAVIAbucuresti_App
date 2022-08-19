export class ParticipateModel{
    id:number;
    announcementId:number;
    mailEuroavia:string;


  constructor(id: number, announcementId: number, mailEuroavia: string) {
    this.id = id;
    this.announcementId = announcementId;
    this.mailEuroavia = mailEuroavia;
  }
}
