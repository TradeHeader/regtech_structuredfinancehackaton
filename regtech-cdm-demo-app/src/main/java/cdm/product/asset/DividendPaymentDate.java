package cdm.product.asset;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.metafields.ReferenceWithMetaAdjustableOrRelativeDate;
import cdm.base.datetime.metafields.ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder;
import cdm.product.asset.DividendDateReference;
import cdm.product.asset.DividendPaymentDate;
import cdm.product.asset.DividendPaymentDate.DividendPaymentDateBuilder;
import cdm.product.asset.DividendPaymentDate.DividendPaymentDateBuilderImpl;
import cdm.product.asset.DividendPaymentDate.DividendPaymentDateImpl;
import cdm.product.asset.meta.DividendPaymentDateMeta;
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
 * A class describing the date on which the dividend will be paid/received. This class is also used to specify the date on which the FX rate will be determined, when applicable.
 * @version ${project.version}
 */
@RosettaDataType(value="DividendPaymentDate", builder=DividendPaymentDate.DividendPaymentDateBuilderImpl.class, version="${project.version}")
public interface DividendPaymentDate extends RosettaModelObject {

	DividendPaymentDateMeta metaData = new DividendPaymentDateMeta();

	/*********************** Getter Methods  ***********************/
	DividendDateReference getDividendDateReference();
	ReferenceWithMetaAdjustableOrRelativeDate getDividendDate();

	/*********************** Build Methods  ***********************/
	DividendPaymentDate build();
	
	DividendPaymentDate.DividendPaymentDateBuilder toBuilder();
	
	static DividendPaymentDate.DividendPaymentDateBuilder builder() {
		return new DividendPaymentDate.DividendPaymentDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DividendPaymentDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DividendPaymentDate> getType() {
		return DividendPaymentDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("dividendDateReference"), processor, DividendDateReference.class, getDividendDateReference());
		processRosetta(path.newSubPath("dividendDate"), processor, ReferenceWithMetaAdjustableOrRelativeDate.class, getDividendDate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DividendPaymentDateBuilder extends DividendPaymentDate, RosettaModelObjectBuilder {
		DividendDateReference.DividendDateReferenceBuilder getOrCreateDividendDateReference();
		DividendDateReference.DividendDateReferenceBuilder getDividendDateReference();
		ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder getOrCreateDividendDate();
		ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder getDividendDate();
		DividendPaymentDate.DividendPaymentDateBuilder setDividendDateReference(DividendDateReference dividendDateReference);
		DividendPaymentDate.DividendPaymentDateBuilder setDividendDate(ReferenceWithMetaAdjustableOrRelativeDate dividendDate0);
		DividendPaymentDate.DividendPaymentDateBuilder setDividendDateValue(AdjustableOrRelativeDate dividendDate1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("dividendDateReference"), processor, DividendDateReference.DividendDateReferenceBuilder.class, getDividendDateReference());
			processRosetta(path.newSubPath("dividendDate"), processor, ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder.class, getDividendDate());
		}
		

		DividendPaymentDate.DividendPaymentDateBuilder prune();
	}

	/*********************** Immutable Implementation of DividendPaymentDate  ***********************/
	class DividendPaymentDateImpl implements DividendPaymentDate {
		private final DividendDateReference dividendDateReference;
		private final ReferenceWithMetaAdjustableOrRelativeDate dividendDate;
		
		protected DividendPaymentDateImpl(DividendPaymentDate.DividendPaymentDateBuilder builder) {
			this.dividendDateReference = ofNullable(builder.getDividendDateReference()).map(f->f.build()).orElse(null);
			this.dividendDate = ofNullable(builder.getDividendDate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("dividendDateReference")
		public DividendDateReference getDividendDateReference() {
			return dividendDateReference;
		}
		
		@Override
		@RosettaAttribute("dividendDate")
		public ReferenceWithMetaAdjustableOrRelativeDate getDividendDate() {
			return dividendDate;
		}
		
		@Override
		public DividendPaymentDate build() {
			return this;
		}
		
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder toBuilder() {
			DividendPaymentDate.DividendPaymentDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DividendPaymentDate.DividendPaymentDateBuilder builder) {
			ofNullable(getDividendDateReference()).ifPresent(builder::setDividendDateReference);
			ofNullable(getDividendDate()).ifPresent(builder::setDividendDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendPaymentDate _that = getType().cast(o);
		
			if (!Objects.equals(dividendDateReference, _that.getDividendDateReference())) return false;
			if (!Objects.equals(dividendDate, _that.getDividendDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dividendDateReference != null ? dividendDateReference.hashCode() : 0);
			_result = 31 * _result + (dividendDate != null ? dividendDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendPaymentDate {" +
				"dividendDateReference=" + this.dividendDateReference + ", " +
				"dividendDate=" + this.dividendDate +
			'}';
		}
	}

	/*********************** Builder Implementation of DividendPaymentDate  ***********************/
	class DividendPaymentDateBuilderImpl implements DividendPaymentDate.DividendPaymentDateBuilder {
	
		protected DividendDateReference.DividendDateReferenceBuilder dividendDateReference;
		protected ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder dividendDate;
	
		public DividendPaymentDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("dividendDateReference")
		public DividendDateReference.DividendDateReferenceBuilder getDividendDateReference() {
			return dividendDateReference;
		}
		
		@Override
		public DividendDateReference.DividendDateReferenceBuilder getOrCreateDividendDateReference() {
			DividendDateReference.DividendDateReferenceBuilder result;
			if (dividendDateReference!=null) {
				result = dividendDateReference;
			}
			else {
				result = dividendDateReference = DividendDateReference.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("dividendDate")
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder getDividendDate() {
			return dividendDate;
		}
		
		@Override
		public ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder getOrCreateDividendDate() {
			ReferenceWithMetaAdjustableOrRelativeDate.ReferenceWithMetaAdjustableOrRelativeDateBuilder result;
			if (dividendDate!=null) {
				result = dividendDate;
			}
			else {
				result = dividendDate = ReferenceWithMetaAdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("dividendDateReference")
		public DividendPaymentDate.DividendPaymentDateBuilder setDividendDateReference(DividendDateReference dividendDateReference) {
			this.dividendDateReference = dividendDateReference==null?null:dividendDateReference.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dividendDate")
		public DividendPaymentDate.DividendPaymentDateBuilder setDividendDate(ReferenceWithMetaAdjustableOrRelativeDate dividendDate) {
			this.dividendDate = dividendDate==null?null:dividendDate.toBuilder();
			return this;
		}
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder setDividendDateValue(AdjustableOrRelativeDate dividendDate) {
			this.getOrCreateDividendDate().setValue(dividendDate);
			return this;
		}
		
		@Override
		public DividendPaymentDate build() {
			return new DividendPaymentDate.DividendPaymentDateImpl(this);
		}
		
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder prune() {
			if (dividendDateReference!=null && !dividendDateReference.prune().hasData()) dividendDateReference = null;
			if (dividendDate!=null && !dividendDate.prune().hasData()) dividendDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDividendDateReference()!=null && getDividendDateReference().hasData()) return true;
			if (getDividendDate()!=null && getDividendDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendPaymentDate.DividendPaymentDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DividendPaymentDate.DividendPaymentDateBuilder o = (DividendPaymentDate.DividendPaymentDateBuilder) other;
			
			merger.mergeRosetta(getDividendDateReference(), o.getDividendDateReference(), this::setDividendDateReference);
			merger.mergeRosetta(getDividendDate(), o.getDividendDate(), this::setDividendDate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendPaymentDate _that = getType().cast(o);
		
			if (!Objects.equals(dividendDateReference, _that.getDividendDateReference())) return false;
			if (!Objects.equals(dividendDate, _that.getDividendDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dividendDateReference != null ? dividendDateReference.hashCode() : 0);
			_result = 31 * _result + (dividendDate != null ? dividendDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendPaymentDateBuilder {" +
				"dividendDateReference=" + this.dividendDateReference + ", " +
				"dividendDate=" + this.dividendDate +
			'}';
		}
	}
}
