export class AnnouncementModel {
  id: number;
  title: string;
  participantCount: number;
  commentCount: number;
  body: string;
  created: string;
  date: string;
  isParticipant: boolean;


  constructor(id: number, title: string, participantCount: number, commentCount: number,
              body: string, created: string, date: string, isParticipant: boolean) {
    this.id = id;
    this.title = title;
    this.participantCount = participantCount;
    this.commentCount = commentCount;
    this.body = body;
    this.created = created;
    this.date = date;
    this.isParticipant = isParticipant;
  }

}
