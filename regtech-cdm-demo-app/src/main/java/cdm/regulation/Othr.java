package cdm.regulation;

import cdm.regulation.DerivInstrmAttrbts;
import cdm.regulation.FinInstrmGnlAttrbts;
import cdm.regulation.Othr;
import cdm.regulation.Othr.OthrBuilder;
import cdm.regulation.Othr.OthrBuilderImpl;
import cdm.regulation.Othr.OthrImpl;
import cdm.regulation.SchmeNm;
import cdm.regulation.meta.OthrMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="Othr", builder=Othr.OthrBuilderImpl.class, version="${project.version}")
public interface Othr extends RosettaModelObject {

	OthrMeta metaData = new OthrMeta();

	/*********************** Getter Methods  ***********************/
	FinInstrmGnlAttrbts getFinInstrmGnlAttrbts();
	DerivInstrmAttrbts getDerivInstrmAttrbts();
	String getId();
	SchmeNm getSchmeNm();

	/*********************** Build Methods  ***********************/
	Othr build();
	
	Othr.OthrBuilder toBuilder();
	
	static Othr.OthrBuilder builder() {
		return new Othr.OthrBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Othr> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Othr> getType() {
		return Othr.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("finInstrmGnlAttrbts"), processor, FinInstrmGnlAttrbts.class, getFinInstrmGnlAttrbts());
		processRosetta(path.newSubPath("derivInstrmAttrbts"), processor, DerivInstrmAttrbts.class, getDerivInstrmAttrbts());
		processor.processBasic(path.newSubPath("id"), String.class, getId(), this);
		processRosetta(path.newSubPath("schmeNm"), processor, SchmeNm.class, getSchmeNm());
	}
	

	/*********************** Builder Interface  ***********************/
	interface OthrBuilder extends Othr, RosettaModelObjectBuilder {
		FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder getOrCreateFinInstrmGnlAttrbts();
		FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder getFinInstrmGnlAttrbts();
		DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder getOrCreateDerivInstrmAttrbts();
		DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder getDerivInstrmAttrbts();
		SchmeNm.SchmeNmBuilder getOrCreateSchmeNm();
		SchmeNm.SchmeNmBuilder getSchmeNm();
		Othr.OthrBuilder setFinInstrmGnlAttrbts(FinInstrmGnlAttrbts finInstrmGnlAttrbts);
		Othr.OthrBuilder setDerivInstrmAttrbts(DerivInstrmAttrbts derivInstrmAttrbts);
		Othr.OthrBuilder setId(String id);
		Othr.OthrBuilder setSchmeNm(SchmeNm schmeNm);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("finInstrmGnlAttrbts"), processor, FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder.class, getFinInstrmGnlAttrbts());
			processRosetta(path.newSubPath("derivInstrmAttrbts"), processor, DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder.class, getDerivInstrmAttrbts());
			processor.processBasic(path.newSubPath("id"), String.class, getId(), this);
			processRosetta(path.newSubPath("schmeNm"), processor, SchmeNm.SchmeNmBuilder.class, getSchmeNm());
		}
		

		Othr.OthrBuilder prune();
	}

	/*********************** Immutable Implementation of Othr  ***********************/
	class OthrImpl implements Othr {
		private final FinInstrmGnlAttrbts finInstrmGnlAttrbts;
		private final DerivInstrmAttrbts derivInstrmAttrbts;
		private final String id;
		private final SchmeNm schmeNm;
		
		protected OthrImpl(Othr.OthrBuilder builder) {
			this.finInstrmGnlAttrbts = ofNullable(builder.getFinInstrmGnlAttrbts()).map(f->f.build()).orElse(null);
			this.derivInstrmAttrbts = ofNullable(builder.getDerivInstrmAttrbts()).map(f->f.build()).orElse(null);
			this.id = builder.getId();
			this.schmeNm = ofNullable(builder.getSchmeNm()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("finInstrmGnlAttrbts")
		public FinInstrmGnlAttrbts getFinInstrmGnlAttrbts() {
			return finInstrmGnlAttrbts;
		}
		
		@Override
		@RosettaAttribute("derivInstrmAttrbts")
		public DerivInstrmAttrbts getDerivInstrmAttrbts() {
			return derivInstrmAttrbts;
		}
		
		@Override
		@RosettaAttribute("id")
		public String getId() {
			return id;
		}
		
		@Override
		@RosettaAttribute("schmeNm")
		public SchmeNm getSchmeNm() {
			return schmeNm;
		}
		
		@Override
		public Othr build() {
			return this;
		}
		
		@Override
		public Othr.OthrBuilder toBuilder() {
			Othr.OthrBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Othr.OthrBuilder builder) {
			ofNullable(getFinInstrmGnlAttrbts()).ifPresent(builder::setFinInstrmGnlAttrbts);
			ofNullable(getDerivInstrmAttrbts()).ifPresent(builder::setDerivInstrmAttrbts);
			ofNullable(getId()).ifPresent(builder::setId);
			ofNullable(getSchmeNm()).ifPresent(builder::setSchmeNm);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Othr _that = getType().cast(o);
		
			if (!Objects.equals(finInstrmGnlAttrbts, _that.getFinInstrmGnlAttrbts())) return false;
			if (!Objects.equals(derivInstrmAttrbts, _that.getDerivInstrmAttrbts())) return false;
			if (!Objects.equals(id, _that.getId())) return false;
			if (!Objects.equals(schmeNm, _that.getSchmeNm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (finInstrmGnlAttrbts != null ? finInstrmGnlAttrbts.hashCode() : 0);
			_result = 31 * _result + (derivInstrmAttrbts != null ? derivInstrmAttrbts.hashCode() : 0);
			_result = 31 * _result + (id != null ? id.hashCode() : 0);
			_result = 31 * _result + (schmeNm != null ? schmeNm.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Othr {" +
				"finInstrmGnlAttrbts=" + this.finInstrmGnlAttrbts + ", " +
				"derivInstrmAttrbts=" + this.derivInstrmAttrbts + ", " +
				"id=" + this.id + ", " +
				"schmeNm=" + this.schmeNm +
			'}';
		}
	}

	/*********************** Builder Implementation of Othr  ***********************/
	class OthrBuilderImpl implements Othr.OthrBuilder {
	
		protected FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder finInstrmGnlAttrbts;
		protected DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder derivInstrmAttrbts;
		protected String id;
		protected SchmeNm.SchmeNmBuilder schmeNm;
	
		public OthrBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("finInstrmGnlAttrbts")
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder getFinInstrmGnlAttrbts() {
			return finInstrmGnlAttrbts;
		}
		
		@Override
		public FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder getOrCreateFinInstrmGnlAttrbts() {
			FinInstrmGnlAttrbts.FinInstrmGnlAttrbtsBuilder result;
			if (finInstrmGnlAttrbts!=null) {
				result = finInstrmGnlAttrbts;
			}
			else {
				result = finInstrmGnlAttrbts = FinInstrmGnlAttrbts.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("derivInstrmAttrbts")
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder getDerivInstrmAttrbts() {
			return derivInstrmAttrbts;
		}
		
		@Override
		public DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder getOrCreateDerivInstrmAttrbts() {
			DerivInstrmAttrbts.DerivInstrmAttrbtsBuilder result;
			if (derivInstrmAttrbts!=null) {
				result = derivInstrmAttrbts;
			}
			else {
				result = derivInstrmAttrbts = DerivInstrmAttrbts.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("id")
		public String getId() {
			return id;
		}
		
		@Override
		@RosettaAttribute("schmeNm")
		public SchmeNm.SchmeNmBuilder getSchmeNm() {
			return schmeNm;
		}
		
		@Override
		public SchmeNm.SchmeNmBuilder getOrCreateSchmeNm() {
			SchmeNm.SchmeNmBuilder result;
			if (schmeNm!=null) {
				result = schmeNm;
			}
			else {
				result = schmeNm = SchmeNm.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("finInstrmGnlAttrbts")
		public Othr.OthrBuilder setFinInstrmGnlAttrbts(FinInstrmGnlAttrbts finInstrmGnlAttrbts) {
			this.finInstrmGnlAttrbts = finInstrmGnlAttrbts==null?null:finInstrmGnlAttrbts.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("derivInstrmAttrbts")
		public Othr.OthrBuilder setDerivInstrmAttrbts(DerivInstrmAttrbts derivInstrmAttrbts) {
			this.derivInstrmAttrbts = derivInstrmAttrbts==null?null:derivInstrmAttrbts.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("id")
		public Othr.OthrBuilder setId(String id) {
			this.id = id==null?null:id;
			return this;
		}
		@Override
		@RosettaAttribute("schmeNm")
		public Othr.OthrBuilder setSchmeNm(SchmeNm schmeNm) {
			this.schmeNm = schmeNm==null?null:schmeNm.toBuilder();
			return this;
		}
		
		@Override
		public Othr build() {
			return new Othr.OthrImpl(this);
		}
		
		@Override
		public Othr.OthrBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Othr.OthrBuilder prune() {
			if (finInstrmGnlAttrbts!=null && !finInstrmGnlAttrbts.prune().hasData()) finInstrmGnlAttrbts = null;
			if (derivInstrmAttrbts!=null && !derivInstrmAttrbts.prune().hasData()) derivInstrmAttrbts = null;
			if (schmeNm!=null && !schmeNm.prune().hasData()) schmeNm = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFinInstrmGnlAttrbts()!=null && getFinInstrmGnlAttrbts().hasData()) return true;
			if (getDerivInstrmAttrbts()!=null && getDerivInstrmAttrbts().hasData()) return true;
			if (getId()!=null) return true;
			if (getSchmeNm()!=null && getSchmeNm().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Othr.OthrBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Othr.OthrBuilder o = (Othr.OthrBuilder) other;
			
			merger.mergeRosetta(getFinInstrmGnlAttrbts(), o.getFinInstrmGnlAttrbts(), this::setFinInstrmGnlAttrbts);
			merger.mergeRosetta(getDerivInstrmAttrbts(), o.getDerivInstrmAttrbts(), this::setDerivInstrmAttrbts);
			merger.mergeRosetta(getSchmeNm(), o.getSchmeNm(), this::setSchmeNm);
			
			merger.mergeBasic(getId(), o.getId(), this::setId);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Othr _that = getType().cast(o);
		
			if (!Objects.equals(finInstrmGnlAttrbts, _that.getFinInstrmGnlAttrbts())) return false;
			if (!Objects.equals(derivInstrmAttrbts, _that.getDerivInstrmAttrbts())) return false;
			if (!Objects.equals(id, _that.getId())) return false;
			if (!Objects.equals(schmeNm, _that.getSchmeNm())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (finInstrmGnlAttrbts != null ? finInstrmGnlAttrbts.hashCode() : 0);
			_result = 31 * _result + (derivInstrmAttrbts != null ? derivInstrmAttrbts.hashCode() : 0);
			_result = 31 * _result + (id != null ? id.hashCode() : 0);
			_result = 31 * _result + (schmeNm != null ? schmeNm.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OthrBuilder {" +
				"finInstrmGnlAttrbts=" + this.finInstrmGnlAttrbts + ", " +
				"derivInstrmAttrbts=" + this.derivInstrmAttrbts + ", " +
				"id=" + this.id + ", " +
				"schmeNm=" + this.schmeNm +
			'}';
		}
	}
}
