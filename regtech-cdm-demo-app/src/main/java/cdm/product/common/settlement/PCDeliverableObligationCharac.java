package cdm.product.common.settlement;

import cdm.product.common.settlement.PCDeliverableObligationCharac;
import cdm.product.common.settlement.PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder;
import cdm.product.common.settlement.PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilderImpl;
import cdm.product.common.settlement.PCDeliverableObligationCharac.PCDeliverableObligationCharacImpl;
import cdm.product.common.settlement.meta.PCDeliverableObligationCharacMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the Partial Cash Deliverable Obligation Characteristic.
 * @version ${project.version}
 */
@RosettaDataType(value="PCDeliverableObligationCharac", builder=PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilderImpl.class, version="${project.version}")
public interface PCDeliverableObligationCharac extends RosettaModelObject {

	PCDeliverableObligationCharacMeta metaData = new PCDeliverableObligationCharacMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Indicates whether the provision is applicable.
	 */
	Boolean getApplicable();
	/**
	 * Specifies whether either &#39;Partial Cash Settlement of Assignable Loans&#39;, &#39;Partial Cash Settlement of Consent Required Loans&#39; or &#39;Partial Cash Settlement of Participations&#39; is applicable. If this element is specified and Assignable Loan is a Deliverable Obligation Characteristic, any Assignable Loan that is deliverable, but where a non-receipt of Consent by the Physical Settlement Date has occurred, the Loan can be cash settled rather than physically delivered. If this element is specified and Consent Required Loan is a Deliverable Obligation Characteristic, any Consent Required Loan that is deliverable, but where a non-receipt of Consent by the Physical Settlement Date has occurred, the Loan can be cash settled rather than physically delivered. If this element is specified and Direct Loan Participation is a Deliverable Obligation Characteristic, any Participation that is deliverable, but where this participation has not been effected (has not come into effect) by the Physical Settlement Date, the participation can be cash settled rather than physically delivered.
	 */
	Boolean getPartialCashSettlement();

	/*********************** Build Methods  ***********************/
	PCDeliverableObligationCharac build();
	
	PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder toBuilder();
	
	static PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder builder() {
		return new PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PCDeliverableObligationCharac> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PCDeliverableObligationCharac> getType() {
		return PCDeliverableObligationCharac.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
		processor.processBasic(path.newSubPath("partialCashSettlement"), Boolean.class, getPartialCashSettlement(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PCDeliverableObligationCharacBuilder extends PCDeliverableObligationCharac, RosettaModelObjectBuilder {
		PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder setApplicable(Boolean applicable);
		PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder setPartialCashSettlement(Boolean partialCashSettlement);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("applicable"), Boolean.class, getApplicable(), this);
			processor.processBasic(path.newSubPath("partialCashSettlement"), Boolean.class, getPartialCashSettlement(), this);
		}
		

		PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder prune();
	}

	/*********************** Immutable Implementation of PCDeliverableObligationCharac  ***********************/
	class PCDeliverableObligationCharacImpl implements PCDeliverableObligationCharac {
		private final Boolean applicable;
		private final Boolean partialCashSettlement;
		
		protected PCDeliverableObligationCharacImpl(PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder builder) {
			this.applicable = builder.getApplicable();
			this.partialCashSettlement = builder.getPartialCashSettlement();
		}
		
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("partialCashSettlement")
		public Boolean getPartialCashSettlement() {
			return partialCashSettlement;
		}
		
		@Override
		public PCDeliverableObligationCharac build() {
			return this;
		}
		
		@Override
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder toBuilder() {
			PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder builder) {
			ofNullable(getApplicable()).ifPresent(builder::setApplicable);
			ofNullable(getPartialCashSettlement()).ifPresent(builder::setPartialCashSettlement);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PCDeliverableObligationCharac _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(partialCashSettlement, _that.getPartialCashSettlement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (partialCashSettlement != null ? partialCashSettlement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PCDeliverableObligationCharac {" +
				"applicable=" + this.applicable + ", " +
				"partialCashSettlement=" + this.partialCashSettlement +
			'}';
		}
	}

	/*********************** Builder Implementation of PCDeliverableObligationCharac  ***********************/
	class PCDeliverableObligationCharacBuilderImpl implements PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder {
	
		protected Boolean applicable;
		protected Boolean partialCashSettlement;
	
		public PCDeliverableObligationCharacBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("applicable")
		public Boolean getApplicable() {
			return applicable;
		}
		
		@Override
		@RosettaAttribute("partialCashSettlement")
		public Boolean getPartialCashSettlement() {
			return partialCashSettlement;
		}
		
	
		@Override
		@RosettaAttribute("applicable")
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder setApplicable(Boolean applicable) {
			this.applicable = applicable==null?null:applicable;
			return this;
		}
		@Override
		@RosettaAttribute("partialCashSettlement")
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder setPartialCashSettlement(Boolean partialCashSettlement) {
			this.partialCashSettlement = partialCashSettlement==null?null:partialCashSettlement;
			return this;
		}
		
		@Override
		public PCDeliverableObligationCharac build() {
			return new PCDeliverableObligationCharac.PCDeliverableObligationCharacImpl(this);
		}
		
		@Override
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getApplicable()!=null) return true;
			if (getPartialCashSettlement()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder o = (PCDeliverableObligationCharac.PCDeliverableObligationCharacBuilder) other;
			
			
			merger.mergeBasic(getApplicable(), o.getApplicable(), this::setApplicable);
			merger.mergeBasic(getPartialCashSettlement(), o.getPartialCashSettlement(), this::setPartialCashSettlement);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PCDeliverableObligationCharac _that = getType().cast(o);
		
			if (!Objects.equals(applicable, _that.getApplicable())) return false;
			if (!Objects.equals(partialCashSettlement, _that.getPartialCashSettlement())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (applicable != null ? applicable.hashCode() : 0);
			_result = 31 * _result + (partialCashSettlement != null ? partialCashSettlement.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PCDeliverableObligationCharacBuilder {" +
				"applicable=" + this.applicable + ", " +
				"partialCashSettlement=" + this.partialCashSettlement +
			'}';
		}
	}
}
