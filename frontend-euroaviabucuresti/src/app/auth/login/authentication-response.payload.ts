export interface AuthenticationResponse{
    authenticationToken: string;
    refreshToken: string;
    expiresAt: Date;
    mailEuroavia: string;
}