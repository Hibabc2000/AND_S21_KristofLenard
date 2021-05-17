package via.andS21.KristofLenard.Model;

public enum VoteType
{
    LIST, //party-list proportional - uses voting_type_list
    FPTP, //first-past-the-post - also used for referendums (yes/no) - uses voting_type_list
    IRV,  //instant-runoff - not yet supported
    STV,  //single transferable vote - not yet supported
    MMP   //mixed-member proportional (AKA additional member system) - uses voting_type_list x2 (once for list, once for constituency)
}
