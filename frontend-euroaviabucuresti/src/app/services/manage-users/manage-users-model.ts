export class ManageUsersModel {
  id: number;
  firstName: string;
  lastName: string;
  mailEuroavia: string;
  department: string;
  activityPoints: number;
  virtualToken:number;


  constructor(id: number, firstName: string, lastName: string, mailEuroavia: string, department: string, activityPoints: number, virtualToken: number) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.mailEuroavia = mailEuroavia;
    this.department = department;
    this.activityPoints = activityPoints;
    this.virtualToken = virtualToken;
  }

}
