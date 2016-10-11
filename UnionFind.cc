#include <iostream>
#include <algorithm>
#include <vector>


#define ll long long 


class UnionFindForests{
    public:
    	vector<ll> parents, ranks;
    	UnionFindForests(ll size){
    		for (ll i = 0; i < size; ++i)
    		{
    			parents.push_back(i);
    			rank.push_back(0);
    		}
    	}

    	void find(ll x){
    		if(parents[x] == x){
    			return x;
    		}else{
    			//　一度根をたどった場合変を直接根に貼り直してる。
    			parents[x] = find(parents[x]);
    		}
    	}

}