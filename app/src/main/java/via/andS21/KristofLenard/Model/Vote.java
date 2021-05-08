package via.andS21.KristofLenard.Model;

import java.util.List;

public class Vote
{
    private Voter voter;
    private VoteType voteType;
    private List<String> votes; //list is needed due to possibility of parallel voting system

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public List<String> getVotes() {
        return votes;
    }

    public void setVotes(List<String> votes) {
        this.votes = votes;
    }
}
