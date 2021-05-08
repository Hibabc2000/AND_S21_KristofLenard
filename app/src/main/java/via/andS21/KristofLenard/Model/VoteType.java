package via.andS21.KristofLenard.Model;

public enum VoteType
{
    LIST, //party-list proportional
    FPTP, //first-past-the-post - also used for referendums (yes/no)
    IRV,  //instant-runoff
    STV,  //single transferable vote
    PARLF //parallel voting - list and FPTP together
}
