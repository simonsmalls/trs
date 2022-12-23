export class Employee {
  id:number;
  abbreviation: string;
  firstName: string;
  lastName: string;
  roles: string[];
  hourlyRate: number;

  hasRole(role:string){
    return this.roles.includes(role);
  }
}
