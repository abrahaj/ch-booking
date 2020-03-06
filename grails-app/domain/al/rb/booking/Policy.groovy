package al.rb.booking

class Policy {
    Policyinfo policyinfo
    ArrayList<PetsPolicy> petsPolicies
    ArrayList<CancelPolicy> cancelPolicies
    ArrayList<GuaranteePayment> guaranteePayments
    ArrayList<TaxPolicy> taxPolicies
    ArrayList<FeePolicy> feePolicies
    static constraints = {
    }
}
