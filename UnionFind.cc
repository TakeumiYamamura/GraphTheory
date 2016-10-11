#include <iostream>
#include <algorithm>
#include <vector>
#define ll long long
using namespace std;

class UnionFindForests{
    public:
    	vector<ll> parents, ranks;
    	UnionFindForests(ll size){
    		for (ll i = 0; i < size; ++i)
    		{
    			parents.push_back(i);
    			ranks.push_back(0);
    		}
    	}

    	ll find(ll x){
    		if(parents[x] == x){
    			return x;
    		}else{
    			//　一度根をたどった場合変を直接根に貼り直してる。
                //pass compression(経路圧縮)
    			parents[x] = find(parents[x]);
    		}
    	}

        bool same(ll x, ll y){
            if(parents[x] == parents[y]){
                return true;
            }else{
                return false;
            }
        }

        void unite(ll x, ll y){
            ll root_x = find(x);
            ll root_y = find(y);
            if (ranks[root_x] > ranks[root_y])
            {
                parents[root_y] = root_x;
            }else if(ranks[root_x] < ranks[root_y]){
                parents[root_x] = root_y;
            }else if(ranks[root_x] == ranks[root_y]){
                parents[root_x] = root_y;
                ranks[root_y]++;
            }
        }
};

int main(){
    UnionFindForests uf = UnionFindForests(10);
    return 0;
}

