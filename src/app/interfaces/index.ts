export interface UserDTO {
    active: boolean;
    id: number;
    mobileNumber: string;
    name: string;
    password: string;
    role: string[];
    username: string;
}

export interface LoginDTO {
    username: string;
    password: string;
    role: string;
}

export interface CourseDTO {
    active: boolean;
    couresDuration: string;
    couresEndDate: string;
    couresFees: string;
    couresName: string;
    couresStartDate: string;
    courseId: number;
}

export interface ForgotPasswordDTO {
    newPassword: string;
    oldPassword: string;
    role: string;
    username: string;
}

export interface ApplicantDTO {
    active: boolean;
    admissionStatus: string;
    applicantDegree: string;
    applicantGraduationPercent: number;
    applicantId: number;
    applicantMobileNumber: string;
    applicantName: string;
    courseId: number;
    email: string;
}

export interface UniversityStraffMemberDTO {
    active: boolean;
    email: string;
    password: string;
    role: string;
    staffId: number;
}
export interface AdmissionDTO {
    admissionId: number;
    admissionDate: string;
    admissionStatus: string;
    course: CourseDTO;
    active: boolean;
}

export interface ApplicantDetailsDTO {
    id: number;
    applicantName: string;
    email: string;
    applicantMobileNumber: string;
    applicantDegree: string;
    applicantGraduationPercent: number;
    admission: AdmissionDTO;
    active: boolean;
}